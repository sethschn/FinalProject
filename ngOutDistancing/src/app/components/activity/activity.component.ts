import { ActivityService } from './../../services/activity.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { ActivityComment } from 'src/app/models/activity-comment';
import { ActivityCommentService } from 'src/app/services/activity-comment.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  activity = new Activity();
  activities: Activity[] = [];
  activityComments: ActivityComment[] = [];
  comment = new ActivityComment();
  editActivity = null;
  newActivity = new Activity();
  selected = null;

  constructor(
    private activitySvc: ActivityService,
    private commentSvc: ActivityCommentService,
    private currentRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const activityIdStr = this.currentRoute.snapshot.paramMap.get('id')
    if (!this.selected && activityIdStr) {
      const activityId = Number.parseInt(activityIdStr,10);
      this.activitySvc.show(activityId).subscribe(
        activity =>{
          this.selected = activity;
          this.loadComments();
        },
        fail => {
          console.error(fail);
          this.router.navigateByUrl(`/notFound`)
        }
      );
    }
    else{
      this.reload();
    }
  }

  loadComments(){
    this.commentSvc.index(this.selected.id).subscribe(
      comments =>{
        this.activityComments = comments;
        console.log(comments);
      },
      fail => {
        console.error(fail);
        // this.router.navigateByUrl(`/notFound`)
      }
    );


  }

  reload(){
    this.activitySvc.index().subscribe(
      data => {
        this.activities = data;
      },
      bad =>{
        console.error('nonUserLanding.reload(): error retrieving activity list')
        console.error(bad);
      }
    );
  }

  loadActivities(){
    this.activitySvc.index().subscribe(
      err => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

  addActivity(activity: Activity){
    this.activitySvc.create(activity).subscribe(
      good => {
        this.reload();
        this.newActivity = new Activity();
      },
      bad =>{
        console.error('workoutListComponent.addWorkout(): error adding workout')
        console.error(bad);
      }
    );
  }

  updateActivity(activity: Activity){
    this.activitySvc.update(activity, activity.id).subscribe(
      yay => {
        this.reload();
        this.editActivity = null;
      },
      boo => {
      console.error('workoutlistComp updating workout error');
      }
    );
  }

  setEditActivity(){
    this.editActivity = Object.assign({}, this.selected);
  }

  displayTable(activities){
    this.selected = null;
    this.router.navigateByUrl(`/activities`);
  }

  displayActivity(activity){
    this.router.navigateByUrl(`/activities/${activity.id}`);
  }

  addComment(comment: ActivityComment, activity: Activity){
    this.commentSvc.createComment(comment, activity.id).subscribe(
      good => {
        this.reload();
        this.newActivity = new Activity();
      },
      bad =>{
        console.error('workoutListComponent.addWorkout(): error adding workout')
        console.error(bad);
      }
    );
  }




}
