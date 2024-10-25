import React from 'react';
import { Ticket } from '../types';
import styles from './ticketsList.module.css';
import { MdDeleteOutline } from 'react-icons/md';
import { MdOutlineEdit } from 'react-icons/md';
import { deleteTicketById } from '../api/ticketsService';

type Props = {
  items: Ticket[];
};

export const TicketsList: React.FC<Props> = ({ items = [] }) => {
  if (items.length === 0) {
    return <div>No tickets available.</div>;
  }

  const handleTicketDeletion = async (id: number | undefined) => {
    if (id) {
      await deleteTicketById(id);
    }
  };

  return (
    <div>
      {items.map((item) => (
        <div key={item.id} className={styles.item}>
          <div>{item.name}</div>
          <div className={styles.price}>{item.price}</div>
          <div>Is sold: {item.sold}</div>
          <div> {item.type}</div>
          <div>
            Coordinates: <div>{item.coordinates.x}</div>
            <div>{item.coordinates.y}</div>
          </div>
          <div>
            Venue:
            <div>{item.venue.name}</div>
            <div>{item.venue.type}</div>
            <div>{item.venue.capacity}</div>
            <div>Address zipcode: {item.venue.address.zipCode}</div>
          </div>
          <button
            onClick={() => {
              handleTicketDeletion(item.id);
            }}
          >
            <MdDeleteOutline />
          </button>
          <button>
            <MdOutlineEdit />
          </button>
        </div>
      ))}
    </div>
  );
};
