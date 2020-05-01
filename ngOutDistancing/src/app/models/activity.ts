export class Activity {


  id: number;
  title: string;
  shortDescription: string;
  enabled: boolean;
  user: string;
  description: string;
  imageUrl: string;
  equipmentLevel: string;
  equipmentDescription: string;



  constructor(
  id?: number,
  title?: string,
  shortDescription?: string,
  enabled?: boolean,
  user?: string,
  description?: string,
  imageUrl?: string,
  equipmentLevel?: string,
  equipmentDescription?: string
  ){
  this.id = id;
  this.title = title;
  this.shortDescription = shortDescription;
  this.enabled = enabled;
  this.user = user;
  this.description = description;
  this.imageUrl = imageUrl;
  this.equipmentLevel = equipmentLevel;
  this.equipmentDescription = equipmentDescription;
  }
}
