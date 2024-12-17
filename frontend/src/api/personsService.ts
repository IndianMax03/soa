import { Sort } from '../types';
import { buildQueryString } from '../util/queryBuilder';
import apiClient from './apiClient';

export const getAllPersons = async (
  page: number,
  size: number,
  sort?: Sort,
  filter?: Map<string, string | number> | undefined
) => {
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
