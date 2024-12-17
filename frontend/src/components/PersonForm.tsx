import React, { useState } from 'react';
import styles from './styles.module.css';
import { createPerson } from '../api/personsService';

export const PersonForm = () => {
  const [name, setName] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const handleCreatePerson = async () => {
    await createPerson(name, password);
  };

  return (
    <div className={styles.form}>
      Create person
      <input
        placeholder="Username"
        type="text"
        onChange={(e) => {
          setName(e.target.value);
        }}
      />
      <input
        placeholder="Password"
        type="password"
        onChange={(e) => {
          setPassword(e.target.value);
        }}
      />
      <button onClick={handleCreatePerson}>Create</button>
    </div>
  );
};
