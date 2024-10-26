import React from 'react';
import { Person } from '../types';
import styles from './personsList.module.css';
import { FaSort } from 'react-icons/fa';

type Props = {
  items: Person[];
};

const imageNames = ['bibizyan.jpg', 'dilf.jpg', 'grandpa.jpg', 'rock.jpg'];

export const PersonsList: React.FC<Props> = ({ items = [] }) => {
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
              Id <FaSort />
            </th>
            <th>
              Username <FaSort />
            </th>
            <th>
              Password <FaSort />
            </th>
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={item.id}>
              <td>
                {' '}
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
