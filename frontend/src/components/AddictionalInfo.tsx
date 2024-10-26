import React, { useState } from 'react';
import { Ticket, Venue } from '../types';
import { getMinimumOfVenue, getSumOfAllTickets, getUniqueVenue } from '../api/ticketsUtilService';
import styles from './addictionalInfo.module.css';

export const AddictionalInfo = () => {
  const [uniqueVenue, setUniqueVenue] = useState<Venue>();
  const [minVenue, setMinVenue] = useState<Ticket>();
  const [ticketsSum, setTicketsSum] = useState<number>();

  const handleGetUniqueVenue = async () => {
    const venue = await getUniqueVenue();
    setUniqueVenue(venue);
  };
  const handleGetMinVenue = async () => {
    const venue = await getMinimumOfVenue();
    setMinVenue(venue);
  };

  const handleGetTicketsSum = async () => {
    const sum = await getSumOfAllTickets();
    setTicketsSum(sum);
  };

  return (
    <div className={styles.block}>
      <div className={styles.result}>
        <button onClick={handleGetUniqueVenue}>Get unique venue</button>

        <div>{uniqueVenue?.name}</div>
        <div>{uniqueVenue?.address.zipCode}</div>
        <div>{uniqueVenue?.type}</div>
        <div>{uniqueVenue?.capacity}</div>
      </div>
      <div className={styles.result}>
        <button onClick={handleGetMinVenue}>Get venue with minimum capacity</button>

        <div>{minVenue?.venue.name}</div>
        <div>{minVenue?.venue.address.zipCode}</div>
        <div>{minVenue?.venue.type}</div>
        <div>{minVenue?.venue.capacity}</div>
      </div>
      <div className={styles.result}>
        <button onClick={handleGetTicketsSum}>Get sum of all tickets</button>
        <div>{ticketsSum}</div>
      </div>
    </div>
  );
};
