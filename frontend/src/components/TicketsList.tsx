import React, { useState } from 'react';
import { Ticket } from '../types';
import styles from './ticketsList.module.css';
import { MdDeleteOutline } from 'react-icons/md';
import { MdOutlineEdit } from 'react-icons/md';
import { deleteTicketById, updateTicketById } from '../api/ticketsService';
import { IoCartOutline } from 'react-icons/io5';
import { FaSort } from 'react-icons/fa';
import { EditTicketPopup } from './EditTicketPopup';

type Props = {
  items: Ticket[]; // Ensure items is an array of Ticket type
  setSelectedTicketId: React.Dispatch<React.SetStateAction<number | undefined>>;
  setPopupIsVisible: React.Dispatch<React.SetStateAction<boolean>>;
};

export const TicketsList: React.FC<Props> = ({
  items = [],
  setSelectedTicketId,
  setPopupIsVisible
}) => {
  const [editPopupVisible, setEditPopupVisible] = useState(false);
  const [ticketToEdit, setTicketToEdit] = useState<Ticket | null>(null);

  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);

  const validatedItems = Array.isArray(items) ? items : [];

  const totalPages = Math.ceil(validatedItems.length / pageSize);

  const handleTicketDeletion = async (id: number | undefined) => {
    if (id) {
      await deleteTicketById(id);
    }
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
              Id <FaSort />
            </th>
            <th>
              Name <FaSort />
            </th>
            <th>
              Price <FaSort />
            </th>
            <th>
              Is Sold <FaSort />
            </th>
            <th>
              Type <FaSort />
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
