import { ActivityComponent } from './../activity/activity.component';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from './../../services/activity.service';
import { Activity } from 'src/app/models/activity';


@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.css']
})
export class UserLandingComponent implements OnInit {

  activity = new Activity();
  activities: Activity[] = [];

  constructor(private userService: UserService,private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService,
    private activitySvc: ActivityService
    ) { }

  ngOnInit(): void {
    this.loadActivities();
  }

  loadActivities(){
    this.activitySvc.index().subscribe(
      data => {
        this.activities = data;
      },
      err => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

}
