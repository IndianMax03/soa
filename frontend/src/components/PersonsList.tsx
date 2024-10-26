import React, { useEffect, useState } from 'react';
import { Person, Sort } from '../types';
import styles from './personsList.module.css';
import { FaSort } from 'react-icons/fa';
import { getAllPersons } from '../api/personsService';
import { CiFilter } from 'react-icons/ci';

type Props = {
  items: Person[];
  setPersons: React.Dispatch<React.SetStateAction<Person[]>>;
  totalPagesCount: number;
  setTotalPagesCount: React.Dispatch<React.SetStateAction<number>>;
  currentPage: number;
  setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
  pageSize: number;
  setPageSize: React.Dispatch<React.SetStateAction<number>>;
};

const imageNames = ['bibizyan.jpg', 'dilf.jpg', 'grandpa.jpg', 'rock.jpg', 'monkey.jpg'];

export const PersonsList: React.FC<Props> = ({
  items = [],
  setPersons,
  totalPagesCount,
  setTotalPagesCount,
  currentPage,
  setCurrentPage,
  pageSize,
  setPageSize
}) => {
  const [sort, setSort] = useState<Sort | undefined>();
  const [filterPopupVisible, setFilterPopupVisible] = useState(false);
  const [filter, setFilter] = useState<Map<string, string | number> | undefined>();

  useEffect(() => {
    const fetchSortedPersons = async () => {
      if (sort && filter) {
        const response = await getAllPersons(currentPage, pageSize, sort, filter);
        const persons = response.PersonResponseArray.persons.person;
        setTotalPagesCount(response.PersonResponseArray.totalPages);
        setPersons(persons);
      }
      else if (sort){
        const response = await getAllPersons(currentPage, pageSize,sort);
        const persons = response.PersonResponseArray.persons.person;
        setTotalPagesCount(response.PersonResponseArray.totalPages);
        setPersons(persons);
      }
      else if (filter){
        const response = await getAllPersons(currentPage, pageSize,undefined, filter);
        const persons = response.PersonResponseArray.persons.person;
        setTotalPagesCount(response.PersonResponseArray.totalPages);
        setPersons(persons);
      }
      else{
        const response = await getAllPersons(currentPage, pageSize);
        const persons = response.PersonResponseArray.persons.person;
        setTotalPagesCount(response.PersonResponseArray.totalPages);
        setPersons(persons);
      }
    };
    fetchSortedPersons();
  }, [sort, setPersons, filter, currentPage, pageSize, setTotalPagesCount]);

  const handleSort = (name: string) => {
    setSort((prevSort) =>
      prevSort?.name === name ? { name, asc: !prevSort.asc } : { name, asc: true }
    );
  };

  const handlePageSizeChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setPageSize(Number(event.target.value));
    setCurrentPage(0);
  };


  if (items.length === 0) {
    return <div>No persons available.</div>;
  }
  return (
    <div className={styles.list}>
      <table className={styles.ticketsTable}>
        <thead>
          <tr>
            <th>Photo</th>
            <th>
              Id
              <FaSort
                onClick={() => {
                  handleSort('id');
                }}
              />
            </th>
            <th>
              Username
              <FaSort
                onClick={() => {
                  handleSort('username');
                }}
              />
            </th>
            <th>
              Password
              <FaSort
                onClick={() => {
                  handleSort('password');
                }}
              />
            </th>
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={item.id}>
              <td>
                <img src={imageNames[index % imageNames.length]} />
              </td>
              <td>{item.id}</td>
              <td>{item.username}</td>
              <td>{item.password}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className={styles.paginationControls}>
        <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 0}>
          Previous
        </button>
        <span>
          Page {currentPage + 1} out of {totalPagesCount}
        </span>
        <button
          onClick={() => setCurrentPage(currentPage + 1)}
          disabled={currentPage + 1 == totalPagesCount}
        >
          Next
        </button>
        <select value={pageSize} onChange={handlePageSizeChange}>
          <option value={5}>5 per page</option>
          <option value={10}>10 per page</option>
          <option value={20}>20 per page</option>
        </select>
        <button onClick={() => setFilterPopupVisible(true)}>
          <CiFilter />
        </button>
      </div>
    </div>
  );
};
