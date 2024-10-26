import axios, { AxiosResponse } from 'axios';
import { xml2json } from '../util/converter';

const apiClient = axios.create({
  baseURL: 'http://localhost:8081/tickets_service',
  headers: {
    'Content-Type': 'application/json'
  }
});

apiClient.interceptors.response.use((response: AxiosResponse) => {
  const parser = new DOMParser(); // initialize dom parser
  const srcDOM = parser.parseFromString(response.data, 'application/xml');
  console.log(xml2json(srcDOM));
  return xml2json(srcDOM);
});

export default apiClient;
