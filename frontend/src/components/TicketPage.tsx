import React, { useEffect, useState } from 'react';
import { getTickets } from '../api/ticketsService';
import { TicketForm } from './TicketForm';
import { Ticket } from '../types';
import { TicketsList } from './TicketsList';
import { AddictionalInfo } from './AddictionalInfo';
import { BuyPopup } from './BuyPopup';

export const TicketPage = () => {
  const [tickets, setTickets] = useState<Ticket[] | undefined>([]);
  const [selectedTicketId, setSelectedTicketId] = useState<number | undefined>();
  const [popupIsVisible, setPopupIsVisible] = useState<boolean>(false);
  const [currentPage, setCurrentPage] = useState(0);
  const [size, setSize] = useState(5);
  const [totalPagesCount, setTotalPagesCount] = useState<number>(1);

  const fetch = async () => {
    const response = await getTickets(currentPage, size);
    const tickets = response.content;
    setTotalPagesCount(response.meta.totalPages);
    setTickets(tickets);
  };

  const handleClose = () => {
    setPopupIsVisible(false);
    fetch();
  };

  useEffect(() => {
    fetch();
  }, [setTickets]);

  return (
    <>
      {popupIsVisible && <BuyPopup selectedTicketId={selectedTicketId} onClose={handleClose} />}
      <TicketsList
        items={tickets}
        setSelectedTicketId={setSelectedTicketId}
        setPopupIsVisible={setPopupIsVisible}
        setTickets={setTickets}
        currentPage={currentPage}
        size={size}
        setCurrentPage={setCurrentPage}
        setSize={setSize}
        totalPagesCount={totalPagesCount}
        setTotalPagesCount={setTotalPagesCount}
      />
      <div style={{ display: 'flex', alignItems: 'flex-start', gap: '20px', marginTop: '20px' }}>
        <TicketForm />
        <AddictionalInfo />
      </div>
    </>
  );
};
