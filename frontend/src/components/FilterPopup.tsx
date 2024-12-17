import React from 'react';
import styles from './filterPopup.module.css';
import { IoIosClose } from 'react-icons/io';
import { FilterType } from './FilterType';
import { Filter, FilterFields } from '../types';

type Props = {
  onClose: () => void;
  filter: Filter;
  setFilter: React.Dispatch<React.SetStateAction<Filter>>;
  filterFields: FilterFields[];
};

export const FilterPopup: React.FC<Props> = ({ onClose, filter, setFilter, filterFields }) => {
  const handleFilterChange = (key: string, value: any) => {
    setFilter((prev) => ({ ...prev, [key]: value }));
  };

  const handleSave = () => {
    onClose();
  };

  return (
    <div className={styles.overlay}>
      <div className={styles.popup}>
        <button className={styles.closeButton} onClick={onClose}>
          <IoIosClose size={30} />
        </button>

        {filterFields.map(({ key, type, placeholder, inputType }) => (
          <div key={key} className={styles.filterField}>
            <input
              type={inputType}
              placeholder={placeholder}
              value={filter?.[key] || ''}
              onChange={(e) =>
                handleFilterChange(
                  key,
                  inputType === 'number'
                    ? Number(e.target.value) || undefined
                    : e.target.value || undefined
                )
              }
            />
            <FilterType
              type={type}
              onFilterChange={(filterType) => handleFilterChange(`${key}Filter`, filterType)}
            />
          </div>
        ))}

        <button onClick={handleSave} className={styles.saveButton}>
          Save Changes
        </button>
      </div>
    </div>
  );
};
