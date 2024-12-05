import { useEffect, useState } from 'react';
import { PersonForm } from './PersonForm';
import { getAllPersons } from '../api/personsService';
import { PersonsList } from './PersonsList';
import { Person } from '../types';

export const PersonsPage = () => {
  const [persons, setPersons] = useState<Person[]>([]);
  const [totalPagesCount, setTotalPagesCount] = useState<number>(1);
  const [currentPage, setCurrentPage] = useState(0);
  const [size, setSize] = useState(5);

  useEffect(() => {
    (async () => {
      const response = await getAllPersons(currentPage, size);
      const persons = response.content;
      setTotalPagesCount(response.meta.totalPages);
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
        size={size}
        setSize={setSize}
      />
    </div>
  );
};
