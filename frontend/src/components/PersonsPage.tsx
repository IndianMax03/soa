import React, { useEffect, useState } from 'react';
import { PersonForm } from './PersonForm';
import { getAllPersons } from '../api/personsService';
import { PersonsList } from './PersonsList';
import { Person } from '../types';

export const PersonsPage = () => {
  const [persons, setPersons] = useState<Person[]>([]);

  useEffect(() => {
    (async () => {
      const persons = await getAllPersons();
      console.log(persons);
      setPersons(persons);
    })();
  }, [setPersons]);

  return (
    <div style={{ display: 'grid', justifyItems: 'center', gap: '20px' }}>
      <PersonForm />
      <PersonsList items={persons} />
    </div>
  );
};
