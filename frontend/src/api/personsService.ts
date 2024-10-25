import apiClient from './apiClient';

export const getAllPersons = async () => {
  try {
    const response = await apiClient.get('/persons');
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
