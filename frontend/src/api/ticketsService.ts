import { Coordinates, Sort, TicketType, Venue } from '../types';
import apiClient from './apiClient';

export const getTickets = async (
  page: number,
  pageSize: number,
  sort?: Sort,
  filter?: Map<string, string | number> | undefined
) => {
  try {
    const params = new URLSearchParams();
    if (sort) {
      params.append('sort', `${sort.name},${sort.asc ? 'asc' : 'desc'}`);
    }
    if (filter) {
      filter.forEach((value, key) => {
        params.append(key, value.toString());
      });
    }
    params.append('page', page.toString());
    params.append('pageSize', pageSize.toString());
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

export const updateTicketById = async (
  id: number,
  name?: string,
  coordinates?: Coordinates,
  price?: number,
  type?: TicketType,
  venue?: Venue
) => {
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
