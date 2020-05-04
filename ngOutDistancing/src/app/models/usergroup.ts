import { User } from './user';
export class Usergroup {
  id: number;
  name: string;
  shortDescription: string;
  enabled: boolean;
  creator: User;
  description: string;
  imageUrl: string;

  constructor(id?: number, name?: string, shortDescription?: string, enabled?: boolean, creator?: User, description?: string, imageUrl?: string){
    this.id = id;
    this.name = name;
    this.shortDescription = shortDescription;
    this.enabled =  enabled;
    this.creator = creator;
    this.description = description;
    this.imageUrl = imageUrl;
  }
}
