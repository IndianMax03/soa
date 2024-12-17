import { Coordinates, Filter, Sort, Ticket, TicketType, Venue } from '../types';
import { buildQueryString } from '../util/queryBuilder';
import apiClient from './apiClient';

export const getTickets = async (page: number, size: number, sort?: Sort, filter?: Filter) => {
  try {
    const params = new URLSearchParams();
    if (sort) {
      params.append('sort', `${sort.name},${sort.asc ? 'asc' : 'desc'}`);
    }
    if (filter) {
      const queryString = buildQueryString(filter);
      queryString.split('&').forEach((param) => {
        const [key, value] = param.split('=');
        if (key && value) {
          params.append(decodeURIComponent(key), decodeURIComponent(value));
        }
      });
    }
    params.append('page', page.toString());
    params.append('size', size.toString());
    const response = await apiClient.get('/tickets', { params });
    return response;
  } catch (error) {
    console.error('Error fetching tickets:', error);
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

export const updateTicketById = async (ticket: Ticket) => {
  try {
    const response = await apiClient.put(`/tickets/${ticket.id}`, {
      name: ticket.name,
      price: ticket.price,
      type: ticket.type,
      venue: ticket.venue,
      coordinates: ticket.coordinates
    });
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
