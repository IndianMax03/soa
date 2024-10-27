import React, { useState } from 'react';
import styles from './editTicketPopup.module.css';
import { IoIosClose } from 'react-icons/io';

type Props = {
  onClose: () => void;
  filter: Map<string, string | number> | undefined;
  setFilter: React.Dispatch<React.SetStateAction<Map<string, string | number> | undefined>>;
};
export const PersonsFilterPopup: React.FC<Props> = ({ onClose, filter, setFilter }) => {
  const [id, setId] = useState(filter?.get('id'));
  const [username, setUsername] = useState(filter?.get('username'));
  const [password, setPassword] = useState(filter?.get('password'));

  const handleSave = () => {
    const newFilter = new Map<string, string | number>();

    if (id) newFilter.set('id', id);
    if (username) newFilter.set('username', username);
    if (password) newFilter.set('password', password);

    setFilter(newFilter.size ? newFilter : undefined);
    onClose();
  };

  return (
    <div className={styles.overlay}>
      <div className={styles.popup}>
        <button className={styles.closeButton} onClick={onClose}>
          <IoIosClose size={30} />
        </button>
        <input
          value={id || ''}
          type="number"
          placeholder="id"
          onChange={(e) => setId(Number(e.target.value) || undefined)}
        />
        <input
          value={username || ''}
          placeholder="username"
          onChange={(e) => setUsername(e.target.value || undefined)}
        />
        <input
          type="text"
          placeholder="password"
          value={password || ''}
          onChange={(e) => setPassword(Number(e.target.value) || undefined)}
        />

        <button onClick={handleSave}>Save Changes</button>
      </div>
    </div>
  );
};
