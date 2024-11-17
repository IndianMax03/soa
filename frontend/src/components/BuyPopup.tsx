import { useEffect, useState } from 'react';
import { Person } from '../types';
import { getAllPersons } from '../api/personsService';
import { sellTicket, sellTicketWithDiscount } from '../api/bookingService';
import { IoIosClose } from 'react-icons/io';
import styles from './BuyPopup.module.css';

type Props = {
  selectedTicketId: number;
  onClose: () => void;
};

export const BuyPopup = ({ selectedTicketId, onClose }: Props) => {
  const [persons, setPersons] = useState<Person[]>([]);
  const [personId, setPersonId] = useState<number | undefined>(undefined);
  const [discount, setDiscount] = useState<number | undefined>(undefined);
  const [price, setPrice] = useState<number>(0);
  const [withDiscount, setWithDiscount] = useState<boolean>(false);

  useEffect(() => {
    (async () => {
      const response = await getAllPersons(0, 100);
      const persons = response.PersonResponseArray.persons.person;
      setPersons(persons);
    })();
  }, []);

  const handleTicketBuy = async () => {
    if (discount && personId) {
      await sellTicketWithDiscount(selectedTicketId, personId, discount);
    } else if (personId) {
      await sellTicket(selectedTicketId, personId, price);
    }
    onClose();
  };

  return (
    <div className={styles.overlay}>
      <div className={styles.popup}>
        <button onClick={onClose} className={styles.closeButton}>
          <IoIosClose />
        </button>
        <select
          value={personId !== undefined ? personId : ''}
          onChange={(e) => setPersonId(Number(e.target.value))}
        >
          <option value="" disabled>
            Select a person
          </option>
          {persons.map((person) => (
            <option key={person.id} value={person.id}>
              {person.id} - {person.username}
            </option>
          ))}
        </select>
        {withDiscount && (
          <>
            <button onClick={() => setWithDiscount(false)}>Without Discount</button>
            <input
              style={{ marginTop: '20px' }}
              placeholder="Enter discount"
              onChange={(e) => setDiscount(Number(e.target.value))}
            />
          </>
        )}
        {!withDiscount && (
          <>
            <button onClick={() => setWithDiscount(true)}>With Discount</button>
            <input
              placeholder="Enter price"
              style={{ marginTop: '20px' }}
              onChange={(e) => setPrice(Number(e.target.value))}
            />
          </>
        )}
        <button onClick={handleTicketBuy}>Buy</button>
      </div>
    </div>
  );
};
