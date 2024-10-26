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
  items: Ticket[];
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
            <th>
              Coordinates (X, Y) <FaSort />
            </th>
            <th>
              Venue Name <FaSort />
            </th>
            <th>
              Venue Type <FaSort />
            </th>
            <th>
              Venue Capacity <FaSort />
            </th>
            <th>
              Address Zipcode <FaSort />
            </th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {items.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td className={styles.price}>{item.price}</td>
              <td>{item.sold ? 'Yes' : 'No'}</td>
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
                <button onClick={() => handleTicketBuying(item.id)}>
                  <IoCartOutline />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

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
