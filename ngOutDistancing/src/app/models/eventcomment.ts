import { User } from './user';

export class Eventcomment {
  id: number;
  content:string;
  enabled:boolean;
  inReplyId: number;
  user: User;
  createDate: string;


  constructor(
    id?: number,
    content?:string,
    enabled?:boolean,
    inReplyId?: number,
    user?: User,
    createDate?: string

  ){
  this.id=id;
  this.content= content;
  this.enabled= enabled;
  this.inReplyId= inReplyId;
  this.user = user;
  this.createDate = createDate;
  }
}
