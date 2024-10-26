import { Sort } from '../types';
import apiClient from './apiClient';

export const getAllPersons = async (
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
    const response = await apiClient.get('/persons', { params });
    return response;
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
