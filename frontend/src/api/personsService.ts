import { Sort } from '../types';
import apiClient from './apiClient';

export const getAllPersons = async (sort?: Sort) => {
  try {
    const params = new URLSearchParams();
    if (sort) {
      params.append('sort', `${sort.name},${sort.asc ? 'asc' : 'desc'}`);
    }
    const response = await apiClient.get('/persons', { params });
    return response.PersonResponseArray.persons.person;
  } catch (error) {
    console.log(error);
  }
};

export const createPerson = async (username: string, password: string) => {
  try {
    const response = await apiClient.post('/persons', {
      username,
      password
    });
    return response.data;
  } catch (error) {
    console.log(error);
  }
};
