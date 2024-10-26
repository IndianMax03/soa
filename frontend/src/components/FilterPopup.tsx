import React, { useState } from 'react';
import styles from './editTicketPopup.module.css';
import { IoIosClose } from 'react-icons/io';

type Props = {
  onClose: () => void;
  filter: Map<string, string | number> | undefined;
  setFilter: React.Dispatch<React.SetStateAction<Map<string, string | number> | undefined>>;
};
export const FilterPopup: React.FC<Props> = ({ onClose, filter, setFilter }) => {
  const [id, setId] = useState(filter?.get('id'));
  const [name, setName] = useState(filter?.get('name'));
  const [price, setPrice] = useState(filter?.get('price'));
  const [isSold, setIsSold] = useState(filter?.get('isSold') || undefined);

  const handleSave = () => {
    const newFilter = new Map<string, string | number>();

    if (id) newFilter.set('id', id);
    if (name) newFilter.set('name', name);
    if (price) newFilter.set('price', price);
    if (isSold) newFilter.set('isSold', isSold);

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
          value={name || ''}
          placeholder="name"
          onChange={(e) => setName(e.target.value || undefined)}
        />
        <input
          type="number"
          placeholder="price"
          value={price || ''}
          onChange={(e) => setPrice(Number(e.target.value) || undefined)}
        />

        <select value={isSold ?? ''} onChange={(e) => setIsSold(e.target.value || undefined)}>
          <option value="">Sold Status (Any)</option>
          <option value="true">Sold</option>
          <option value="false">Not Sold</option>
        </select>

        <button onClick={handleSave}>Save Changes</button>
      </div>
    </div>
  );
};
