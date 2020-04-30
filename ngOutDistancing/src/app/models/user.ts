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

  constructor(id?: number, username?: string, password?: string, email?: string, firstName?: string, lastName?: string, enabled?: boolean, description?: string, imageUrl?: string, location?: Location, role?: string){
    this.id = id;
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
  }
}
