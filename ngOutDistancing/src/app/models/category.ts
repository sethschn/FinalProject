export class Category {
  id: number;
  type: string;
  shortDescription: string;
  enabled: boolean;
  description: string;
  imageUrl: string;


  constructor(id?: number, type?: string, shortDescription?: string,
    enabled?: boolean, description?: string, imageUrl?: string) {
      this.id = id;
      this.type = type;
      this.shortDescription = shortDescription;
      this.enabled = enabled;
      this.description = description;
      this.imageUrl = imageUrl;
    }
}
