import React, { useEffect, useState } from 'react';
import { PersonForm } from './PersonForm';
import { getAllPersons } from '../api/personsService';
import { PersonsList } from './PersonsList';
import { Person } from '../types';

export const PersonsPage = () => {
  const [persons, setPersons] = useState<Person[]>([]);
  const [totalPagesCount, setTotalPagesCount] = useState<number>(1);
  const [currentPage, setCurrentPage] = useState(0);
  const [pageSize, setPageSize] = useState(5);

  useEffect(() => {
    (async () => {
      const response = await getAllPersons(currentPage, pageSize);
      const persons = response.PersonResponseArray.persons.person;
      setTotalPagesCount(response.PersonResponseArray.totalPages);
      setPersons(persons);
    })();
  }, [setPersons]);

  return (
    <div style={{ display: 'grid', justifyItems: 'center', gap: '20px' }}>
      <PersonForm />
      <PersonsList
        items={persons}
        setPersons={setPersons}
        totalPagesCount={totalPagesCount}
        setTotalPagesCount={setTotalPagesCount}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
        pageSize={pageSize}
        setPageSize={setPageSize}
      />
    </div>
  );
};
