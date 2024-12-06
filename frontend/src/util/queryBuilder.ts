import { Filter } from '../types';

export const buildQueryString = (filter: Filter) => {
  const params = Object.entries(filter)
    .filter(([_, value]) => value !== undefined && value !== '')
    .map(([key, value]) => {
      let transformedKey = key;

      if (key.startsWith('venueName')) {
        transformedKey = key.replace('venueName', 'venue.name');
      }

      if (key.startsWith('venueCapacity')) {
        transformedKey = key.replace('venueCapacity', 'venue.capacity');
      }

      if (transformedKey.endsWith('Filter')) {
        transformedKey = transformedKey.replace('Filter', '-filter');
      }

      return `${encodeURIComponent(transformedKey)}=${encodeURIComponent(value)}`;
    })
    .join('&');

  console.log(params);
  return params;
};
