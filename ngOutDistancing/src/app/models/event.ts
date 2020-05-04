import { Activity } from './activity';

export class Event {
  id:number;
  title: string;
  eventTime: string;
  eventDate: string;
  shortDescription: string;
  enabled: boolean;
  description: string;
  imageUrl: string;
activity: Activity;

  constructor(id?:number,title?: string,eventTime?: string,
    eventDate?: string,shortDescription?: string,enabled?: boolean,
    description?: string,imageUrl?: string, activity?: Activity){
    this.id= id;
    this.title= title;
    this.eventTime= eventTime;
    this.eventDate= eventDate;
    this.shortDescription= shortDescription;
    this.enabled= enabled;
    this.description= description;
    this.imageUrl= imageUrl;
    this.activity= activity;
    }

}
