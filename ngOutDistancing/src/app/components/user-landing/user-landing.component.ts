import { EventService } from './../../services/event.service';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from './../../services/activity.service';
import { Activity } from 'src/app/models/activity';
import { Event } from 'src/app/models/event';


@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.css']
})
export class UserLandingComponent implements OnInit {

  activity = new Activity();
  activities: Activity[] = [];

  event = new Event();
  events: Event[] = [];

  constructor(private userService: UserService,private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService,
    private activitySvc: ActivityService, private eventSvc: EventService
    ) { }

  ngOnInit(): void {
    this.loadActivities();
    this.loadEvents();
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
  loadEvents(){
    this.eventSvc.index().subscribe(
      data => {
        this.events = data;
      },
      err => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

}
