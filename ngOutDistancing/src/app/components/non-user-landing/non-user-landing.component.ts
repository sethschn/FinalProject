import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Component, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ActivityService } from 'src/app/services/activity.service';



@Component({
  selector: 'app-non-user-landing',
  templateUrl: './non-user-landing.component.html',
  styleUrls: ['./non-user-landing.component.css']
})



export class NonUserLandingComponent implements OnInit {

  activity = new Activity();
  activities: Activity[] = [];


  selectedActivity = null;

  constructor(private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService,
    private activitySvc: ActivityService,
  ) { }

  ngOnInit(): void {
    this.loadActivities();
  }

  loadActivities(){
    this.activitySvc.index().subscribe(
      data => {
        console.log(data);
        this.activities = data;
      },
      err => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

  displayActivity(activity: Activity){
    this.router.navigateByUrl(`/activities/${activity.id}`);
  }


}
