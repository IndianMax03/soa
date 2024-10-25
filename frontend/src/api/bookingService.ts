import apiClient from './apiClient';

export const sellTicket = async (ticketId: number, personId: number, price: number) => {
  try {
    const response = await apiClient.post(`/${ticketId}/${personId}/${price}`);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const sellTicketWithDiscount = async (
  ticketId: number,
  personId: number,
  discount: number
) => {
  try {
    const response = await apiClient.post(`/${ticketId}/${personId}/${discount}`);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};
