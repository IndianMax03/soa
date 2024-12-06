import React, { useEffect, useState } from 'react';
import { FilterInputType, Person, Sort } from '../types';
import styles from './personsList.module.css';
import { FaSort } from 'react-icons/fa';
import { getAllPersons } from '../api/personsService';
import { CiFilter } from 'react-icons/ci';
import { FilterPopup } from './FilterPopup';

type Props = {
  items: Person[];
  setPersons: React.Dispatch<React.SetStateAction<Person[]>>;
  totalPagesCount: number;
  setTotalPagesCount: React.Dispatch<React.SetStateAction<number>>;
  currentPage: number;
  setCurrentPage: React.Dispatch<React.SetStateAction<number>>;
  size: number;
  setSize: React.Dispatch<React.SetStateAction<number>>;
};

const filterFields = [
  { key: 'id', type: FilterInputType.DIGITS, placeholder: 'ID', inputType: 'number' },
  { key: 'name', type: FilterInputType.TEXT, placeholder: 'Name', inputType: 'text' },
  { key: 'password', type: FilterInputType.DIGITS, placeholder: 'Password', inputType: 'text' },
  { key: 'balance', type: FilterInputType.TEXT, placeholder: 'Balance', inputType: 'number' }
];

const imageNames = ['bibizyan.jpg', 'dilf.jpg', 'grandpa.jpg', 'rock.jpg', 'monkey.jpg'];

export const PersonsList: React.FC<Props> = ({
  items = [],
  setPersons,
  totalPagesCount,
  setTotalPagesCount,
  currentPage,
  setCurrentPage,
  size,
  setSize
}) => {
  const [sort, setSort] = useState<Sort | undefined>();
  const [filterPopupVisible, setFilterPopupVisible] = useState(false);
  const [filter, setFilter] = useState<{
    id: undefined;
    name: undefined;
    nameFilter: undefined;
    price: undefined;
    priceFilter: undefined;
    venueName: undefined;
    venueNameFilter: undefined;
    venueCapacity: undefined;
    venueCapacityFilter: undefined;
    zipCode: undefined;
    zipCodeFilter: undefined;
  }>();

  useEffect(() => {
    if (!Array.isArray(items)) {
      const arr: Array<Person> = [];
      arr.push(items);
      console.log(arr);
      setPersons(arr);
    }
  }, [items, setPersons]);

  useEffect(() => {
    const fetchSortedPersons = async () => {
      if (sort && filter) {
        const response = await getAllPersons(currentPage, size, sort, filter);
        const persons = response.content;
        setTotalPagesCount(response.meta.totalPages);
        setPersons(persons);
      } else if (sort) {
        const response = await getAllPersons(currentPage, size, sort);
        const persons = response.content;
        setTotalPagesCount(response.meta.totalPages);
        setPersons(persons);
      } else if (filter) {
        const response = await getAllPersons(currentPage, size, undefined, filter);
        const persons = response.content;
        setTotalPagesCount(response.meta.totalPages);
        setPersons(persons);
      } else {
        const response = await getAllPersons(currentPage, size);
        const persons = response.content;
        setTotalPagesCount(response.meta.totalPages);
        setPersons(persons);
      }
    };
    fetchSortedPersons();
  }, [sort, setPersons, filter, currentPage, size, setTotalPagesCount]);

  const handleSort = (name: string) => {
    setSort((prevSort) =>
      prevSort?.name === name ? { name, asc: !prevSort.asc } : { name, asc: true }
    );
  };

  const handleSizeChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSize(Number(event.target.value));
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
            <th>
              Balance
              <FaSort
                onClick={() => {
                  handleSort('balance');
                }}
              />
            </th>
          </tr>
        </thead>
        <tbody>
          {items.length > 0 ? (
            items.map((item, index) => (
              <tr key={item.id}>
                <td>
                  <img src={imageNames[index % imageNames.length]} />
                </td>
                <td>{item.id}</td>
                <td>{item.username}</td>
                <td>{item.password} </td>
                <td>{item.balance} </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={11} style={{ textAlign: 'center' }}>
                No persons available.
              </td>
            </tr>
          )}
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
        <select value={size} onChange={handleSizeChange}>
          <option value={5}>5 per page</option>
          <option value={10}>10 per page</option>
          <option value={20}>20 per page</option>
        </select>
        <button onClick={() => setFilterPopupVisible(true)}>
          <CiFilter />
        </button>
      </div>

      {filterPopupVisible && (
        <FilterPopup
          onClose={() => setFilterPopupVisible(false)}
          filter={filter}
          setFilter={setFilter}
          filterFields={filterFields}
        />
      )}
    </div>
  );
};
