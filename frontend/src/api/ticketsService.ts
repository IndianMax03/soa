import { Coordinates, TicketType, Venue } from '../types';
import apiClient from './apiClient';

export const getTickets = async () => {
  try {
    const response = await apiClient.get('/tickets');
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const createTicket = async (
  name: string,
  coordinates: Coordinates,
  price: number,
  type: TicketType,
  venue: Venue
) => {
  try {
    const response = await apiClient.post('/tickets', {
      name,
      coordinates,
      price,
      type,
      venue
    });
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const getTicketById = async (id: number) => {
  try {
    const response = await apiClient.get(`/tickets/${id}`);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const updateTicketById = async (id: number) => {
  try {
    const response = await apiClient.put(`/tickets/${id}`);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const deleteTicketById = async (id: number) => {
  try {
    const response = await apiClient.delete(`/tickets/${id}`);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};
