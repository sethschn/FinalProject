import { ActivityService } from './../../services/activity.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';

@Component({
  selector: 'app-non-user-landing',
  templateUrl: './non-user-landing.component.html',
  styleUrls: ['./non-user-landing.component.css']
})
export class NonUserLandingComponent implements OnInit {
  activity = new Activity();
  activities: Activity[] = [];
  selected = null;
  newActivity = new Activity();
  editActivity = null;

  constructor(
    private activitySvc: ActivityService,
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



}
