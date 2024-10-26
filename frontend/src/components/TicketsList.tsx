import React, { useEffect, useState } from 'react';
import { Filter, Sort, Ticket } from '../types';
import styles from './ticketsList.module.css';
import { MdDeleteOutline } from 'react-icons/md';
import { MdOutlineEdit } from 'react-icons/md';
import { deleteTicketById, getTickets, updateTicketById } from '../api/ticketsService';
import { IoCartOutline } from 'react-icons/io5';
import { FaSort } from 'react-icons/fa';
import { EditTicketPopup } from './EditTicketPopup';
import { CiFilter } from 'react-icons/ci';
import { FilterPopup } from './FilterPopup';

type Props = {
  items: Ticket[];
  setSelectedTicketId: React.Dispatch<React.SetStateAction<number | undefined>>;
  setPopupIsVisible: React.Dispatch<React.SetStateAction<boolean>>;
  setTickets: React.Dispatch<React.SetStateAction<Ticket[]>>;
  currentPage: number;
  pageSize: number;
  setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
  setPageSize: React.Dispatch<React.SetStateAction<number>>;
  totalPagesCount: number;
  setTotalPagesCount: React.Dispatch<React.SetStateAction<number>>;
};

export const TicketsList: React.FC<Props> = ({
  items,
  setSelectedTicketId,
  setPopupIsVisible,
  setTickets,
  currentPage,
  pageSize,
  setCurrentPage,
  setPageSize,
  totalPagesCount,
  setTotalPagesCount
}) => {
  const [editPopupVisible, setEditPopupVisible] = useState(false);
  const [filterPopupVisible, setFilterPopupVisible] = useState(false);
  const [ticketToEdit, setTicketToEdit] = useState<Ticket | null>(null);
  const [sort, setSort] = useState<Sort | undefined>();
  const [filter, setFilter] = useState<Map<string, string | number> | undefined>();

  const handleTicketDeletion = async (id: number | undefined) => {
    if (id) {
      await deleteTicketById(id);
    }
  };

  useEffect(() => {
    if (!Array.isArray(items)) {
      const arr: Array<Ticket> = [];
      arr.push(items);
      setTickets(arr);
    }
  }, [items, setTickets]);

  useEffect(() => {
    const fetchSortedTickets = async () => {
      if (sort && filter) {
        const response = await getTickets(currentPage, pageSize, sort, filter);
        const tickets = response.TicketResponseArray?.tickets?.ticket;
        setTotalPagesCount(response.TicketResponseArray.totalPages);
        setTickets(tickets);
      } else if (sort) {
        const response = await getTickets(currentPage, pageSize, sort);
        const tickets = response.TicketResponseArray?.tickets?.ticket;
        setTotalPagesCount(response.TicketResponseArray.totalPages);
        setTickets(tickets);
      } else if (filter) {
        const response = await getTickets(currentPage, pageSize, undefined, filter);
        const tickets = response.TicketResponseArray?.tickets?.ticket;
        setTotalPagesCount(response.TicketResponseArray.totalPages);
        setTickets(tickets);
      } else {

        const response = await getTickets(currentPage, pageSize);
        const tickets = response.TicketResponseArray?.tickets?.ticket;
        setTotalPagesCount(response.TicketResponseArray.totalPages);
        setTickets(tickets);
      }
    };

    fetchSortedTickets();
  }, [sort, setTickets, pageSize, currentPage, filter, setTotalPagesCount]);

  const handleSort = (name: string) => {
    setSort((prevSort) =>
      prevSort?.name === name ? { name, asc: !prevSort.asc } : { name, asc: true }
    );
  };

  const handleTicketBuying = (id: number) => {
    setSelectedTicketId(id);
    setPopupIsVisible(true);
  };

  const handleTicketEdit = (ticket: Ticket) => {
    setTicketToEdit(ticket);
    setEditPopupVisible(true);
  };

  const handlePageSizeChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setPageSize(Number(event.target.value));
    setCurrentPage(1);
  };
  return (
    <div>
      <table className={styles.ticketsTable}>
        <thead>
          <tr>
            <th>
              Id <FaSort onClick={() => handleSort('id')} />
            </th>
            <th>
              Name <FaSort onClick={() => handleSort('name')} />
            </th>
            <th>
              Price <FaSort onClick={() => handleSort('price')} />
            </th>
            <th>
              Is Sold <FaSort onClick={() => handleSort('sold')} />
            </th>
            <th>Type</th>
            <th>Coordinates (X, Y)</th>
            <th>Venue Name</th>
            <th>Venue Type</th>
            <th>Venue Capacity</th>
            <th>Address Zipcode</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {items.length > 0 ? (
            items.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td className={styles.price}>{item.price}</td>
                <td>{item.sold}</td>
                <td>{item.type}</td>
                <td>
                  {item.coordinates.x}, {item.coordinates.y}
                </td>
                <td>{item.venue.name}</td>
                <td>{item.venue.type}</td>
                <td>{item.venue.capacity}</td>
                <td>{item.venue.address.zipCode}</td>
                <td>
                  <button onClick={() => handleTicketDeletion(item.id)}>
                    <MdDeleteOutline />
                  </button>
                  <button onClick={() => handleTicketEdit(item)}>
                    <MdOutlineEdit />
                  </button>
                  {item.sold === 'false' && (
                    <button onClick={() => handleTicketBuying(item.id)}>
                      <IoCartOutline />
                    </button>
                  )}
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={11} style={{ textAlign: 'center' }}>
                No tickets available.
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <div className={styles.paginationControls}>
        <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 0}>
          Previous
        </button>
        <span>
          Page {currentPage + 1} out of {totalPagesCount}
        </span>
        <button
          onClick={() => setCurrentPage(currentPage + 1)}
          disabled={currentPage + 1 == totalPagesCount}
        >
          Next
        </button>
        <select value={pageSize} onChange={handlePageSizeChange}>
          <option value={5}>5 per page</option>
          <option value={10}>10 per page</option>
          <option value={20}>20 per page</option>
        </select>
        <button onClick={() => setFilterPopupVisible(true)}>
          <CiFilter />
        </button>
      </div>

      {filterPopupVisible && (
        <FilterPopup
          onClose={() => setFilterPopupVisible(false)}
          filter={filter}
          setFilter={setFilter}
        />
      )}

      {editPopupVisible && ticketToEdit && (
        <EditTicketPopup
          ticket={ticketToEdit}
          onClose={() => setEditPopupVisible(false)}
          onSave={(updatedTicket) => {
            updateTicketById(updatedTicket.id, updatedTicket);
            setEditPopupVisible(false);
          }}
        />
      )}
    </div>
  );
};
