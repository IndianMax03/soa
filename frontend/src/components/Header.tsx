import React from 'react';
import { Link } from 'react-router-dom';
import styles from './header.module.css';

export const Header = () => {
  return (
    <div className={styles.header}>
      <Link to={'/tickets'}>Tickets</Link>
      <Link to={'/persons'}>Persons</Link>
    </div>
  );
};
