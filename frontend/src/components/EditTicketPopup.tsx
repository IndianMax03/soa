import React, { useState } from 'react';
import { Ticket } from '../types';
import styles from './editTicketPopup.module.css';
import { IoIosClose } from 'react-icons/io';

type Props = {
  ticket: Ticket;
  onClose: () => void;
  onSave: (updatedTicket: Ticket) => void;
};

export const EditTicketPopup: React.FC<Props> = ({ ticket, onClose, onSave }) => {
  const [name, setName] = useState(ticket.name);
  const [price, setPrice] = useState(ticket.price);
  const [coordinates, setCoordinates] = useState(ticket.coordinates);
  const [type, setType] = useState(ticket.type);
  const [venue, setVenue] = useState(ticket.venue);

  const handleSave = () => {
    onSave({
      ...ticket,
      name,
      price,
      coordinates,
      type,
      venue
    });
  };

  return (
    <div className={styles.overlay}>
      <div className={styles.popup}>
        <button className={styles.closeButton} onClick={onClose}>
          <IoIosClose size={30} />
        </button>
        <h3>Edit Ticket</h3>
        <input value={name} onChange={(e) => setName(e.target.value)} />
        <input type="number" value={price} onChange={(e) => setPrice(Number(e.target.value))} />
        <label>Coordinates</label>
        <input
          type="number"
          placeholder="X"
          value={coordinates.x}
          onChange={(e) => setCoordinates({ ...coordinates, x: Number(e.target.value) })}
        />
        <input
          type="number"
          placeholder="Y"
          value={coordinates.y}
          onChange={(e) => setCoordinates({ ...coordinates, y: Number(e.target.value) })}
        />
        <label>Type</label>
        <input value={type} onChange={(e) => setType(e.target.value)} />
        <label>Venue</label>
        <input value={venue.name} onChange={(e) => setVenue({ ...venue, name: e.target.value })} />
        <input value={venue.type} onChange={(e) => setVenue({ ...venue, type: e.target.value })} />
        <input
          type="number"
          value={venue.capacity}
          onChange={(e) => setVenue({ ...venue, capacity: Number(e.target.value) })}
        />
        <input
          value={venue.address.zipCode}
          onChange={(e) =>
            setVenue({
              ...venue,
              address: { ...venue.address, zipCode: e.target.value }
            })
          }
        />
        <button onClick={handleSave}>Save Changes</button>
      </div>
    </div>
  );
};
