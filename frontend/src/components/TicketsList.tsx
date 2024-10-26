import React, { useEffect, useState } from 'react';
import { Sort, Ticket } from '../types';
import styles from './ticketsList.module.css';
import { MdDeleteOutline } from 'react-icons/md';
import { MdOutlineEdit } from 'react-icons/md';
import { deleteTicketById, getTickets, updateTicketById } from '../api/ticketsService';
import { IoCartOutline } from 'react-icons/io5';
import { FaSort } from 'react-icons/fa';
import { EditTicketPopup } from './EditTicketPopup';

type Props = {
  items: Ticket[]; // Ensure items is an array of Ticket type
  setSelectedTicketId: React.Dispatch<React.SetStateAction<number | undefined>>;
  setPopupIsVisible: React.Dispatch<React.SetStateAction<boolean>>;
  setTickets: React.Dispatch<React.SetStateAction<Ticket[]>>;
};

export const TicketsList: React.FC<Props> = ({
  items = [],
  setSelectedTicketId,
  setPopupIsVisible,
  setTickets
}) => {
  const [editPopupVisible, setEditPopupVisible] = useState(false);
  const [ticketToEdit, setTicketToEdit] = useState<Ticket | null>(null);
  const [sort, setSort] = useState<Sort | undefined>();
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);

  const validatedItems = Array.isArray(items) ? items : [];

  const totalPages = Math.ceil(validatedItems.length / pageSize);

  const handleTicketDeletion = async (id: number | undefined) => {
    if (id) {
      await deleteTicketById(id);
    }
  };

  useEffect(() => {
    const fetchSortedTickets = async () => {
      if (sort) {
        const tickets = await getTickets(sort);
        setTickets(tickets);
      }
    };
    fetchSortedTickets();
  }, [sort, setTickets]);

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

  const paginatedItems = validatedItems.slice((currentPage - 1) * pageSize, currentPage * pageSize);

  if (paginatedItems.length === 0) {
    return <div>No tickets found</div>;
  }

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
            <th>
              Type <FaSort onClick={() => handleSort('type')} />
            </th>
            <th>Coordinates (X, Y)</th>
            <th>Venue Name</th>
            <th>Venue Type</th>
            <th>Venue Capacity</th>
            <th>Address Zipcode</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {paginatedItems.map((item) => (
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
          ))}
        </tbody>
      </table>

      <div className={styles.paginationControls}>
        <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 1}>
          Previous
        </button>
        <span>
          Page {currentPage} of {totalPages}
        </span>
        <button
          onClick={() => setCurrentPage(currentPage + 1)}
          disabled={currentPage === totalPages}
        >
          Next
        </button>

        <select value={pageSize} onChange={handlePageSizeChange}>
          <option value={5}>5 per page</option>
          <option value={10}>10 per page</option>
          <option value={20}>20 per page</option>
        </select>
      </div>

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
