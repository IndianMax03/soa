import React, { useEffect, useState } from 'react';
import { FilterInputType, Sort, Ticket } from '../types';
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
  size: number;
  setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
  setSize: React.Dispatch<React.SetStateAction<number>>;
  totalPagesCount: number;
  setTotalPagesCount: React.Dispatch<React.SetStateAction<number>>;
};

const filterFields = [
  { key: 'id', type: FilterInputType.DIGITS, placeholder: 'ID', inputType: 'number' },
  { key: 'name', type: FilterInputType.TEXT, placeholder: 'Name', inputType: 'text' },
  { key: 'price', type: FilterInputType.DIGITS, placeholder: 'Price', inputType: 'number' },
  { key: 'venueName', type: FilterInputType.TEXT, placeholder: 'Venue Name', inputType: 'text' },
  {
    key: 'venueCapacity',
    type: FilterInputType.DIGITS,
    placeholder: 'Venue Capacity',
    inputType: 'number'
  },
  { key: 'zipCode', type: FilterInputType.DIGITS, placeholder: 'Zip Code', inputType: 'number' }
];

export const TicketsList: React.FC<Props> = ({
  items,
  setSelectedTicketId,
  setPopupIsVisible,
  setTickets,
  currentPage,
  size,
  setCurrentPage,
  setSize,
  totalPagesCount,
  setTotalPagesCount
}) => {
  const [editPopupVisible, setEditPopupVisible] = useState(false);
  const [filterPopupVisible, setFilterPopupVisible] = useState(false);
  const [ticketToEdit, setTicketToEdit] = useState<Ticket | null>(null);
  const [sort, setSort] = useState<Sort | undefined>();
  const [filter, setFilter] = useState<{
    id: undefined;
    idFilter: undefined;
    name: undefined;
    nameFilter: undefined;
    price: undefined;
    priceFilter: undefined;
    venueName: undefined;
    venueNameFilter: undefined;
    venueCapacity: undefined;
    venueCapacityFilter: undefined;
    zipCode: undefined;
    zipCodeFilter: undefined;
  }>();

  useEffect(() => {
    if (!Array.isArray(items) && items) {
      const arr: Array<Ticket> = [];
      arr.push(items);
      setTickets(arr);
    }
  }, [items, setTickets]);

  const fetchSortedTickets = async () => {
    console.log(filter);
    if (sort && filter) {
      const response = await getTickets(currentPage, size, sort, filter);
      const tickets = await response.content;
      setTotalPagesCount(response.meta.totalPages);
      await setTickets(tickets);
    } else if (sort) {
      const response = await getTickets(currentPage, size, sort);
      const tickets = await response.content;
      setTotalPagesCount(response.meta.totalPages);
      await setTickets(tickets);
    } else if (filter) {
      const response = await getTickets(currentPage, size, undefined, filter);
      const tickets = await response.content;
      setTotalPagesCount(response.meta.totalPages);
      await setTickets(tickets);
    } else {
      const response = await getTickets(currentPage, size);
      const tickets = await response.content;
      setTotalPagesCount(response.meta.totalPages);
      await setTickets(tickets);
    }
  };

  useEffect(() => {
    fetchSortedTickets();
  }, [sort, setTickets, size, currentPage, filter, setTotalPagesCount]);

  const handleSort = (name: string) => {
    setSort((prevSort) =>
      prevSort?.name === name ? { name, asc: !prevSort.asc } : { name, asc: true }
    );
  };

  const handleClose = async () => {
    await setEditPopupVisible(false);
  };

  const handleTicketDeletion = async (id: number | undefined) => {
    if (id) {
      await deleteTicketById(id);
      fetchSortedTickets();
    }
  };

  const handleTicketEdit = (ticket: Ticket) => {
    setTicketToEdit(ticket);
    setEditPopupVisible(true);
  };

  const handleTicketBuying = (id: number) => {
    setSelectedTicketId(id);
    setPopupIsVisible(true);
  };

  const handleSizeChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSize(Number(event.target.value));
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
            <th>Is Sold</th>
            <th>Type</th>
            <th>Coordinates (X, Y)</th>
            <th>
              Venue Name <FaSort onClick={() => handleSort('venue.name')} />
            </th>
            <th>Venue Type</th>
            <th>
              Venue Capacity <FaSort onClick={() => handleSort('venue.capacity')} />
            </th>
            <th>
              Address Zipcode <FaSort onClick={() => handleSort('venue.address.zipCode')} />
            </th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {items && items.length > 0 ? (
            items.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td className={styles.price}>{item.price}</td>
                <td>{item.sold.toString()}</td>
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
                  {item.sold.toString() === 'false' && (
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
        <select value={size} onChange={handleSizeChange}>
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
          filterFields={filterFields}
        />
      )}

      {editPopupVisible && ticketToEdit && (
        <EditTicketPopup
          ticket={ticketToEdit}
          onClose={handleClose}
          onSave={(updatedTicket) => {
            updateTicketById(updatedTicket);
            setEditPopupVisible(false);
          }}
        />
      )}
    </div>
  );
};
