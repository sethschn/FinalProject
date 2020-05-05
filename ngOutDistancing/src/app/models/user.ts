import { Usergroup } from './usergroup';
import { GroupedObservable } from 'rxjs';

export class User {
  id: number;
  username: string;
  password: string;
  email: string;
  firstName: string;
  lastName: string;
  enabled: boolean;
  description: string;
  imageUrl: string;
  location: Location;
  role: string;
  events: Event[];
  groups: Usergroup[];
  userEvents: Event[];


  constructor(id?: number, groups?: Usergroup[],events?: Event[],username?: string, password?: string, email?: string, firstName?: string, lastName?: string, enabled?: boolean, description?: string, imageUrl?: string, location?: Location, role?: string, userEvents?: Event[]){
    this.id = id;
    this.events = events;
    this.groups = groups;
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.enabled =  enabled;
    this.description = description;
    this.imageUrl = imageUrl;
    this.location = location;
    this.role = role;
    this.userEvents = userEvents? userEvents: [];
  }
}
