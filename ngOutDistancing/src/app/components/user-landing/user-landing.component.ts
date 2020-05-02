import { CategoryService } from './../../services/category.service';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { EventService } from './../../services/event.service';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from './../../services/activity.service';
import { Activity } from 'src/app/models/activity';
import { Event } from 'src/app/models/event';
import { Category } from 'src/app/models/category';


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

  selectedCategory:Category = new Category(0, "All");
  categories: Category[] = [];

  selectedActivity = null;


  constructor(private userService: UserService,private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService,
    private activitySvc: ActivityService, private eventSvc: EventService, private catSvc: CategoryService
    ) { }

  ngOnInit(): void {
    this.loadActivities();
    this.loadEvents();
    this.loadCategories();
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
  loadCategories(){
    this.catSvc.indexCategory().subscribe(
      data => {
        const cat = new Category();
        cat.type = "All";
        this.selectedCategory = cat;
        this.categories = data;
        this.categories.unshift(cat);

      },
      err => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

  displayActivity(activity){
    this.router.navigateByUrl(`/activities/${activity.id}`);
  }



}
