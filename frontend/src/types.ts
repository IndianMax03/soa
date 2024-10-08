export enum TicketType {
    VIP,
    BUDGETARY,
    CHEAP
}

export enum VenueType{
    BAR,
    CINEMA,
    STADIUM
}

export type Address = {
    zipcode: string
}

export type Venue = {
    id: number,
    name: string,
    capacity: number,
    type: VenueType,
    address: Address
}

export type Coordinates = {
    x: number,
    y: number
}