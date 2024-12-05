package com.brigada.tickets_ejb.repository;

import com.brigada.general.model.dto.PageDto;
import com.brigada.general.model.dto.PageMetadata;
import com.brigada.general.model.enums.TicketType;
import com.brigada.general.model.enums.VenueType;
import com.brigada.tickets_ejb.filter.FilterCriterion;
import com.brigada.tickets_ejb.model.Ticket;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
@Transactional
public class TicketsRepository {

    private final List<String> sortingFields = List.of("id", "name", "coordinates.x", "coordinates.y", "is_sold", "price", "type", "venue.id", "venue.name", "venue.capacity", "venue.type", "venue.address.id", "venue.address.zip_code");

    @PersistenceContext(unitName = "TicketsSource")
    private EntityManager entityManager;

    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Ticket.class, id));
    }

    public PageDto<Ticket> findAll(int page, int size, List<String> sortParams, List<FilterCriterion> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
        Root<Ticket> ticket = cq.from(Ticket.class);

        List<Predicate> predicates = buildPredicates(cb, ticket, filters);

        cq.where(predicates.toArray(new Predicate[0]));

        List<Order> orderList = buildOrderList(cb, ticket, sortParams);
        if (!orderList.isEmpty()) {
            cq.orderBy(orderList);
        }

        List<Ticket> tickets = entityManager.createQuery(cq)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Ticket> ticketCount = countQuery.from(Ticket.class);

        countQuery.select(cb.count(ticketCount));
        countQuery.where(buildPredicates(cb, ticketCount, filters).toArray(new Predicate[0]));

        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return constructPage(tickets, page, size, totalCount);
    }

    public void save(Ticket ticket) {
        if (ticket.getId() == 0) {
            entityManager.persist(ticket);
        } else {
            entityManager.merge(ticket);
        }
    }

    public void delete(Ticket ticket) {
        entityManager.remove(ticket);
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Ticket> ticket, List<FilterCriterion> filters) {
        List<Predicate> predicates = new ArrayList<>();

        for (FilterCriterion filter : filters) {
            String fieldName = filter.getFieldName();
            String filterMode = filter.getFilterMode();
            String value = filter.getValue();

            Path<?> path = ticket;

            String[] fieldParts = fieldName.split("\\.");
            for (int i = 0; i < fieldParts.length - 1; i++) {
                path = ((From<?, ?>) path).join(fieldParts[i]);
            }
            String actualField = fieldParts[fieldParts.length - 1];

            switch (filterMode) {
                case "startsWith":
                    predicates.add(cb.like(path.get(actualField), value + "%"));
                    break;
                case "contains":
                    predicates.add(cb.like(path.get(actualField), "%" + value + "%"));
                    break;
                case "notContains":
                    predicates.add(cb.notLike(path.get(actualField), "%" + value + "%"));
                    break;
                case "endsWith":
                    predicates.add(cb.like(path.get(actualField), "%" + value));
                    break;
                case "equals":
                    predicates.add(cb.equal(path.get(actualField), value));
                    break;
                case "notEquals":
                    predicates.add(cb.notEqual(path.get(actualField), value));
                    break;
                case "lt":
                    if (actualField.equals("type")) {
                        predicates.add(cb.lessThan(path.get(actualField), TicketType.valueOf(value)));
                    } else if (actualField.equals("venue.type")) {
                        predicates.add(cb.lessThan(path.get(actualField), VenueType.valueOf(value)));
                    } else {
                        predicates.add(cb.lessThan(path.get(actualField), value));
                    }
                    break;
                case "lte":
                    predicates.add(cb.lessThanOrEqualTo(path.get(actualField), value));
                    break;
                case "gt":
                    predicates.add(cb.greaterThan(path.get(actualField), value));
                    break;
                case "gte":
                    predicates.add(cb.greaterThanOrEqualTo(path.get(actualField), value));
                    break;
                case "dateIs":
                    predicates.add(cb.equal(path.get(actualField), LocalDate.parse(value)));
                    break;
                case "dateIsNot":
                    predicates.add(cb.notEqual(path.get(actualField), LocalDate.parse(value)));
                    break;
                case "dateBefore":
                    predicates.add(cb.lessThan(path.get(actualField), LocalDate.parse(value)));
                    break;
                case "dateAfter":
                    predicates.add(cb.greaterThan(path.get(actualField), LocalDate.parse(value)));
                    break;
            }
        }
        return predicates;
    }

    private List<Order> buildOrderList(CriteriaBuilder cb, Root<Ticket> root, List<String> sortParams) {
        List<Order> orders = new ArrayList<>();

        if (sortParams != null) {
            for (String sortParam : sortParams) {
                String[] parts = sortParam.split(",");

                String field = parts[0].trim();
                String direction = parts[1].trim().toLowerCase();

                From<?, ?> path = root;

                String[] fieldParts = field.split("\\.");
                for (int i = 0; i < fieldParts.length - 1; i++) {
                    path = path.join(fieldParts[i]);
                }

                String actualField = fieldParts[fieldParts.length - 1];

                if ("asc".equals(direction)) {
                    orders.add(cb.asc(path.get(actualField)));
                } else if ("desc".equals(direction)) {
                    orders.add(cb.desc(path.get(actualField)));
                } else {
                    throw new IllegalArgumentException("Unsupported sort direction: " + direction);
                }
            }
        }

        return orders;
    }

    private PageDto<Ticket> constructPage(List<Ticket> tickets, int page, int size, long totalElements) {
        int totalPages = (int) Math.ceil((double) totalElements / size);
        return new PageDto<>(tickets, new PageMetadata(size, page, totalElements, totalPages));
    }

}
