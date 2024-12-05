import axios, { AxiosResponse } from 'axios';
import { xml2json } from '../util/converter';

const apiClient = axios.create({
  baseURL: 'http://localhost:9912',
  headers: {
    'Content-Type': 'application/json'
  }
});

apiClient.interceptors.response.use((response: AxiosResponse) => {
  return response.data;
});

export default apiClient;
