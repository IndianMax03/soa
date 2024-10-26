export enum TicketType {
  VIP = 'VIP',
  BUDGETARY = 'Budgetary',
  CHEAP = 'Cheap'
}

export enum VenueType {
  BAR = 'Bar',
  CINEMA = 'Cinema',
  STADIUM = 'Stadium'
}

export type Address = {
  // length > 10
  zipCode: string;
};

export type Venue = {
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
};

export type Ticket = {
  id?: number;
  name: string;
  coordinates: Coordinates;
  price: number;
  sold: boolean;
  type: TicketType;
  venue: Venue;
};
