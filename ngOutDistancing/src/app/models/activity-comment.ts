import { Activity } from './activity';
import { User } from './user';
export class ActivityComment {

    id: number;
		content: string;
		enabled: boolean;
		user: User;
		parentComment: number;
		activity: Activity;

    constructor(
      id?: number,
      content?: string,
      enabled?: boolean,
      user?: User,
      parentComment?: number,
      activity?: Activity
    ){
  this.id = id;
		this.content = content;
		this.enabled = enabled;
		this.user = user;
		this.parentComment = parentComment;
    this.activity = activity;
    }
}
