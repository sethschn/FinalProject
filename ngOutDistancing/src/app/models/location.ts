export class Location {
  id: number;
  street: string;
  city: string;
  state: string;
  country: string;
  postalCode: string;
  title: string;
  locationUrl: string;

  constructor(id?: number, street?: string, city?: string, state?: string, country?: string, postalCode?: string, title?: string, locationUrl?: string) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postalCode = postalCode;
    this.title = title;
    this.locationUrl = locationUrl;
  }
}
