export class Resource {
  id: number;
  name: string;
  description: string;
  link: string;
  imageUrl: string;


constructor(id?: number, name?: string, description?: string, link?: string, imageUrl?: string) {
  this.id = id;
  this.name = name;
  this.description = description;
  this.link = link;
  this.imageUrl = imageUrl;

}
}
