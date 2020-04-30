import { User } from './user';
export class Usergroup {
  id: number;
  name: string;
  shortDescription: string;
  enabled: boolean;
  user: User;
  description: string;
  imageUrl: string;

  constructor(id?: number, name?: string, shortDescription?: string, enabled?: boolean, user?: User, description?: string, imageUrl?: string){
    this.id = id;
    this.name = name;
    this.shortDescription = shortDescription;
    this.enabled =  enabled;
    this.user = user;
    this.description = description;
    this.imageUrl = imageUrl;
  }
}
