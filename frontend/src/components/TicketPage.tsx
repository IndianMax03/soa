import React, { useEffect, useState } from 'react';
import { getTickets } from '../api/ticketsService';
import { TicketForm } from './TicketForm';
import { Ticket } from '../types';
import { TicketsList } from './TicketsList';
import { AddictionalInfo } from './AddictionalInfo';

export const TicketPage = () => {
  const [tickets, setTickets] = useState<Ticket[]>([]);

  useEffect(() => {
    (async () => {
      const tickets = await getTickets();
      setTickets(tickets);
    })();
  }, [setTickets]);

  return (
    <>
      <TicketForm />
      <AddictionalInfo />
      <TicketsList items={tickets} />
    </>
  );
};
