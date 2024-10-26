import React, { useEffect, useState } from 'react';
import { Person, Sort } from '../types';
import styles from './personsList.module.css';
import { FaSort } from 'react-icons/fa';
import { getAllPersons } from '../api/personsService';

type Props = {
  items: Person[];
  setPersons: React.Dispatch<React.SetStateAction<Person[]>>;
};

const imageNames = ['bibizyan.jpg', 'dilf.jpg', 'grandpa.jpg', 'rock.jpg', 'monkey.jpg'];

export const PersonsList: React.FC<Props> = ({ items = [], setPersons }) => {
  const [sort, setSort] = useState<Sort | undefined>();

  useEffect(() => {
    const fetchSortedPersons = async () => {
      if (sort) {
        const persons = await getAllPersons(sort);
        setPersons(persons);
      }
    };
    fetchSortedPersons();
  }, [sort, setPersons]);

  const handleSort = (name: string) => {
    setSort((prevSort) =>
      prevSort?.name === name ? { name, asc: !prevSort.asc } : { name, asc: true }
    );
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
              Id{' '}
              <FaSort
                onClick={() => {
                  handleSort('id');
                }}
              />
            </th>
            <th>
              Username{' '}
              <FaSort
                onClick={() => {
                  handleSort('username');
                }}
              />
            </th>
            <th>
              Password{' '}
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
    </div>
  );
};
