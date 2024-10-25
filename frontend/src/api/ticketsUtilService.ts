import apiClient from './apiClient';

export const getSumOfAllTickets = async () => {
  try {
    const response = await apiClient.get('/tickets/price/sum');
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const getMinimumOfVenue = async () => {
  try {
    const response = await apiClient.get('/tickets/venue/min');
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const getUniqueVenue = async () => {
  try {
    const response = await apiClient.get('/tickets/venue/unique');
    return response.data;
  } catch (error) {
    console.log(error);
  }
};
