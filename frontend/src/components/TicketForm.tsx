import React, { useState } from 'react';
import styles from './styles.module.css';
import { TicketType, VenueType } from '../types';
import { createTicket } from '../api/ticketsService';

export const TicketForm = () => {
  const [name, setName] = useState<string>('');
  const [x, setX] = useState<number>(0);
  const [y, setY] = useState<number>(0);
  const [price, setPrice] = useState<number>(0);
  const [ticketType, setTicketType] = useState<TicketType>(TicketType.BUDGETARY);
  const [venueName, setVenueName] = useState<string>('');
  const [capacity, setCapacity] = useState<number>(1);
  const [venueType, setVenueType] = useState<VenueType>(VenueType.BAR);
  const [zipCode, setZipCode] = useState<string>('');

  const handleTicketCreation = () => {
    createTicket(name, { x, y }, price, ticketType, {
      name: venueName,
      capacity,
      type: venueType,
      address: { zipCode }
    });
  };

  return (
    <div className={styles.form}>
      <div>Create ticket</div>
      <input placeholder="Name" type="text" onChange={(e) => setName(e.target.value)} />
      <input
        placeholder="X coordinate"
        type="number"
        onChange={(e) => setX(Number(e.target.value))}
      />
      <input
        placeholder="Y coordinate"
        type="number"
        onChange={(e) => setY(Number(e.target.value))}
      />
      <input placeholder="Price" type="number" onChange={(e) => setPrice(Number(e.target.value))} />
      <select
        value={ticketType}
        onChange={(e) => setTicketType(e.target.value as unknown as TicketType)}
      >
        {Object.values(TicketType).map((ticketType) => (
          <option key={ticketType} value={ticketType}>
            {ticketType}
          </option>
        ))}
      </select>
      <div>Venue</div>
      <input placeholder="Name" type="text" onChange={(e) => setVenueName(e.target.value)} />
      <input
        placeholder="Capacity"
        type="number"
        onChange={(e) => setCapacity(Number(e.target.value))}
      />
      <select
        value={venueType}
        onChange={(e) => setVenueType(e.target.value as unknown as VenueType)}
      >
        {Object.values(VenueType).map((venueType) => (
          <option key={venueType} value={venueType}>
            {venueType}
          </option>
        ))}
      </select>
      <div>Address</div>
      <input placeholder="Zip code" type="text" onChange={(e) => setZipCode(e.target.value)} />
      <button onClick={handleTicketCreation}>Create</button>
    </div>
  );
};
