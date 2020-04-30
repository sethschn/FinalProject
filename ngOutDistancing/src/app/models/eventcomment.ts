export class Eventcomment {
  id: number;
  content:string;
  enabled:boolean;
  inReplyId: number;

  constructor(id?: number,content?:string,enabled?:boolean,inReplyId?: number
  ){
  this.id=id;
  this.content= content;
  this.enabled= enabled;
  this.inReplyId= inReplyId;

  }
}
