import React, { useEffect, useState } from 'react';
import { getTickets } from '../api/ticketsService';
import { TicketForm } from './TicketForm';
import { Ticket } from '../types';
import { TicketsList } from './TicketsList';
import { AddictionalInfo } from './AddictionalInfo';
import { BuyPopup } from './BuyPopup';

export const TicketPage = () => {
  const [tickets, setTickets] = useState<Ticket[]>([]);
  const [selectedTicketId, setSelectedTicketId] = useState<number | undefined>();
  const [popupIsVisible, setPopupIsVisible] = useState<boolean>(false);

  useEffect(() => {
    (async () => {
      const tickets = await getTickets();
      setTickets(tickets);
    })();
  }, [setTickets]);

  return (
    <>
      {popupIsVisible && (
        <BuyPopup selectedTicketId={selectedTicketId} onClose={() => setPopupIsVisible(false)} />
      )}
      <TicketsList
        items={tickets}
        setSelectedTicketId={setSelectedTicketId}
        setPopupIsVisible={setPopupIsVisible}
        setTickets={setTickets}
      />
      <div style={{ display: 'flex', alignItems: 'flex-start', gap: '20px', marginTop: '20px' }}>
        <TicketForm />
        <AddictionalInfo />
      </div>
    </>
  );
};
