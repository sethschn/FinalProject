import { User } from './user';

export class Eventcomment {
  id: number;
  content:string;
  enabled:boolean;
  inReplyId: number;
  user: User;
  createDate: string;
  event: Event;


  constructor(
    id?: number,
    content?:string,
    enabled?:boolean,
    inReplyId?: number,
    user?: User,
    createDate?: string,
    event?: Event
  ){
  this.id=id;
  this.content= content;
  this.enabled= enabled;
  this.inReplyId= inReplyId;
  this.user = user;
  this.createDate = createDate;
  this.event = event;
  }
}
