import { Activity } from './activity';
import { User } from './user';
export class ActivityComment {

    id: number;
		content: string;
		enabled: boolean;
		user: User;
		parentComment: ActivityComment;
    activity: Activity;
    replies: ActivityComment[];
    createDate: string;

    constructor(
      id?: number,
      content?: string,
      enabled?: boolean,
      user?: User,
      parentComment?: ActivityComment,
      activity?: Activity,
      replies?: ActivityComment[],
      createDate?: string

    ){
  this.id = id;
		this.content = content;
		this.enabled = enabled;
		this.user = user;
		this.parentComment = parentComment;
    this.activity = activity;
    this.replies = replies? replies: [];
    this.createDate = createDate;
    }
}
