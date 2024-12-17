import { FilterInputType } from '../types';
import styles from './filterType.module.css';

type Props = {
  type: FilterInputType;
  onFilterChange: (value: string) => void;
};

const DIGIT_OPTIONS = [
  { value: 'gte', label: 'Greater than or equals' },
  { value: 'gt', label: 'Greater than' },
  { value: 'lte', label: 'Less than or equals' },
  { value: 'lt', label: 'Less than' }
];

const TEXT_OPTIONS = [
  { value: 'startsWith', label: 'Starts with' },
  { value: 'contains', label: 'Contains' },
  { value: 'notContains', label: 'Does not contain' },
  { value: 'endsWith', label: 'Ends with' },
  { value: 'equals', label: 'Equals' },
  { value: 'notEquals', label: 'Does not equal' }
];

export const FilterType: React.FC<Props> = ({ type, onFilterChange }) => {
  const options = type === FilterInputType.DIGITS ? DIGIT_OPTIONS : TEXT_OPTIONS;

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onFilterChange(e.target.value);
  };

  return (
    <form className={styles.form}>
      {options.map((option) => (
        <label key={option.value}>
          <input type="radio" value={option.value} name="filter" onChange={handleChange} />
          {option.label}
        </label>
      ))}
    </form>
  );
};
