import { EventCommentService } from './../../services/event-comment.service';
import { Eventcomment } from './../../models/eventcomment';
import { UsergroupService } from './../../services/usergroup.service';
import { Usergroup } from './../../models/usergroup';
import { User } from 'src/app/models/user';
import { Event } from 'src/app/models/event';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Activity } from 'src/app/models/activity';
import { ActivityComment } from 'src/app/models/activity-comment';
import { ActivityService } from 'src/app/services/activity.service';
import { ActivityCommentService } from 'src/app/services/activity-comment.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Resource } from 'src/app/models/resource';
import { ResourceService } from 'src/app/services/resource.service';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';
import { UserService } from 'src/app/services/user.service';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AdminComponent implements OnInit {

  activity = new Activity();
  activities: Activity[] = [];
  closeResult = '';
  comment = new ActivityComment();
  currentActivityId = null;
  editCurrentActivity = null;
  newActivity = new Activity();

  selectedActivity = null;

  selectedResource = null;
  newResource = new Resource();
  editResource = null;
  resourceList: Resource[] = [];

  selectedLocation = null;
  newLocation = new Location();
  editLocation = null;
  locationList: Location[] = [];


  selectedUser = null;
  newUser = new User();
  users: User[] = [];

  selectedEvent = null;
  newEvent = new Event();
  events: Event[] = [];

  selectedGroup = null;
  newGroup = new Usergroup();
  groups: Usergroup[] = [];

  selectedEventComment = null;
  newEventComment = new Eventcomment();
  eventComments: Eventcomment[] = [];

  selectedActivityComment = null;
  newComment = new ActivityComment();
  activityComments: ActivityComment[] = [];


  constructor(

    private activitySvc: ActivityService,
    private commentSvc: ActivityCommentService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private resourceSvc: ResourceService,
    private locationSvc: LocationService,
    private userService: UserService,
    private eventService: EventService,
    private groupService: UsergroupService,
    private evCommentSvc: EventCommentService

  ) { }

  ngOnInit(): void {
    this.loadResources();
    this.loadLocations();
    this.loadUsers();
    this.loadEvents();
    this.loadGroups();
    this.reloadActivity();
  }


  reloadActivity(){
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


  deleteActivity(id: number) {
    this.activitySvc.disableActivity(id).subscribe(
      data=> {
        this.reloadActivity();
        this.selectedActivity = null;
      },
      err => {
        console.error('error in deleting activity component')
      }
    )
  }

loadResources(){
  this.resourceSvc.indexResource().subscribe(
    data => {this.resourceList = data;
    },
    bad => {
      console.error("EventDetailComponent.loadEvents(): error loading");
      console.error(bad);
    }
  );
}

deleteResource(resourceId){
  this.resourceSvc.deleteResource(resourceId).subscribe(

    data => {
      this.loadResources();
      this.selectedResource = null;

    },
    err => {
      console.error("Delete Resource in Admin(): error deleting" + err);
    })
  };

  displayLocation(location){
    this.router.navigateByUrl(`/locations/${location.id}`);
 }

loadLocations(){
  this.locationSvc.indexLocation().subscribe(
    data => {this.locationList = data;
    },
    bad => {
      console.error("LocationDetailComponent.loadLocations(): error loading");
      console.error(bad);
    }
  );
}

  deleteALocation(locationId) {
    this.locationSvc.deleteALocation(locationId).subscribe(
      data => {
        this.loadLocations();
        this.selectedLocation = null;
      },
      err => {
        console.error("LocationDetailListComponent.deleteLocation(): error deleting" + err);
      })
  };




  loadUsers(){
    this.userService.index().subscribe(
      data => {this.users = data;
      },
      bad => {
        console.error("loadUsers in admin(): error loading");
        console.error(bad);
      }
    );
  }

  deleteUser(id: number) {
    this.userService.disableUser(id).subscribe(
      data=> {
        this.loadUsers();
        this.selectedUser = null;
      },
      err => {
        console.error('error in deleting user component')
      }
    )
  }
  loadEvents(){
    this.eventService.index().subscribe(
      data => {this.events = data;
      },
      bad => {
        console.error("loadEvents in admin(): error loading");
        console.error(bad);
      }
    );
  }

  deleteEvent(id: number) {
    this.eventService.destroy(id).subscribe(
      data=> {
        this.loadEvents();
        this.selectedEvent = null;
      },
      err => {
        console.error('error in deleting event component')
      }
    )
  }

  loadGroups(){
    this.groupService.index().subscribe(
      data => {this.groups = data;
      },
      bad => {
        console.error("loadGroups in admin(): error loading");
        console.error(bad);
      }
    );
  }

  deleteGroup(id: number) {
    this.groupService.destroy(id).subscribe(
      data=> {
        this.loadGroups();
        this.selectedGroup = null;
      },
      err => {
        console.error('error in deleting group component')
      }
    )
  }
public navigateToSection(section: string) {
      window.location.hash = '';
      window.location.hash = section;
}




}
