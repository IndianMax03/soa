export enum TicketType {
  VIP = 'VIP',
  BUDGETARY = 'BUDGETARY',
  CHEAP = 'CHEAP'
}

export enum VenueType {
  BAR = 'BAR',
  CINEMA = 'CINEMA',
  STADIUM = 'STADIUM'
}

export type FilterFields = {
  key: string;
  type: FilterInputType;
  placeholder: string;
  inputType: string;
};
export type Address = {
  // length > 10
  zipCode: string;
};

export enum FilterInputType {
  DIGITS,
  TEXT
}

export type Filter = {
  id: number | undefined;
  idFilter: string | undefined;
  name: string | undefined;
  nameFilter: string | undefined;
  price: number | undefined;
  priceFilter: string | undefined;
  venueName: string | undefined;
  venueNameFilter: string | undefined;
  venueCapacity: number | undefined;
  venueCapacityFilter: string | undefined;
  zipCode: string | undefined;
  zipCodeFilter: string | undefined;
};

export type Venue = {
  id?: number;
  name: string;
  capacity: number;
  type: VenueType;
  address: Address;
};

export type Coordinates = {
  x: number;
  y: number;
};

export type Person = {
  id?: number;
  username: string;
  password: string;
  balance: number;
};

export type Ticket = {
  id?: number;
  name: string;
  coordinates: Coordinates;
  price: number;
  sold: boolean;
  type: TicketType;
  owner: Person | null;
  venue: Venue;
};

export type Sort = {
  name: string;
  asc: boolean;
};
