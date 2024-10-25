import React from 'react';
import { Person } from '../types';
import styles from './personsList.module.css';

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
      {items.map((item, index) => (
        <div key={item.username} className={styles.imageAndInfo}>
          <img src={imageNames[index % imageNames.length]} />
          <div className={styles.info}>
            <div> {item.username}</div>
            <div> {item.password}</div>
          </div>
        </div>
      ))}
    </div>
  );
};
