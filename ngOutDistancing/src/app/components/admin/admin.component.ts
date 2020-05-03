import { User } from 'src/app/models/user';
import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  activity = new Activity();
  activities: Activity[] = [];
  activityComments: ActivityComment[] = [];
  closeResult = '';
  comment = new ActivityComment();
  currentActivityId = null;
  editCurrentActivity = null;
  newActivity = new Activity();
  newComment = new ActivityComment();
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

  constructor(

    private activitySvc: ActivityService,
    private commentSvc: ActivityCommentService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private resourceSvc: ResourceService,
    private locationSvc: LocationService,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    const activityIdStr = this.currentRoute.snapshot.paramMap.get('id');
    console.log('selected:' + this.selectedActivity);
    console.log('activityIdStr: ' + activityIdStr);
    this.loadResources();
    this.loadLocations();
    this.loadUsers();
    if (!this.selectedActivity && activityIdStr) {
      this.currentActivityId = activityIdStr;
      const activityId = Number.parseInt(activityIdStr,10);
      this.activitySvc.show(activityId).subscribe(
        activity =>{
          this.selectedActivity = activity;
          this.loadComments();
        },
        fail => {
          console.error(fail);
          this.router.navigateByUrl(`/notFound`)
        }
      );
    }
    else{
      this.reloadActivity();
    }
  }

  loadComments(){
    this.commentSvc.index(this.selectedActivity.id).subscribe(
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

  addNewActivity(activity: Activity){
    this.activitySvc.create(activity).subscribe(
      good => {
        this.reloadActivity();
        this.newActivity = new Activity();
      },
      bad =>{
        console.error('workoutListComponent.addWorkout(): error adding workout')
        console.error(bad);
      }
    );
  }

  updateCurrentActivity(activity: Activity){
    console.log('update activity: ' + activity)
    this.activitySvc.update(activity).subscribe(
      yay => {
        this.reloadActivity();
        // this.editCurrentActivity = null;
        this.selectedActivity = this.editCurrentActivity;
      },
      boo => {
      console.error('activity component updating activity error');
    }
    );
  }

  setEditActivity(){
    this.editCurrentActivity = Object.assign({}, this.selectedActivity);
  }

  displayActivityTable(activities){
    this.selectedActivity = null;
    this.router.navigateByUrl(`/activities`);
  }

  displayActivity(activity){
    console.log('--------------------');
    console.log(activity);
    this.router.navigateByUrl(`/activities/${activity.id}`);
  }

  addComment(comment: ActivityComment){
    this.commentSvc.createComment(comment, this.currentActivityId).subscribe(
      good => {
        this.newComment= new ActivityComment();
        // this.selected = null;
        console.log(this.currentActivityId);
        // this.router.navigateByUrl(`/activities/${this.currentActivityId}`);
        this.loadComments();
      },
      bad =>{
        console.error('activity.component.ts addComment(): error adding comment')
        console.error(bad);
      }
    );
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
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

  displayResource(resource){
    this.router.navigateByUrl(`/resources/${resource.id}`);
 }

 showResource(id){
  this.resourceSvc.showResource((this.currentRoute.snapshot.paramMap.get('id'))).subscribe(
    good => {
      this.loadResources();
    },
    bad => {
      console.error("Admin.show(): error")
    }
  )
};

displayResourceTable(){
  this.selectedResource = null;
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

createResource(resource: Resource){
  console.log(resource);
  this.resourceSvc.createResource(resource).subscribe(
    good => {
      this.loadResources();
      this.newResource = new Resource();
    },
    bad => {
      console.error("AdminComponent.createResource(): error adding");
      console.error(bad);
    })
}

setEditResource(){
  this.editResource = Object.assign({}, this.selectedResource)
}

updateResource(resource: Resource){
  console.log(resource);
 this.resourceSvc.updateResource(resource).subscribe(
   good =>{
     this.loadResources();
     this.editResource= null;
     this.selectedResource = this.editResource;
   },
   bad => {
     console.error("AdminComponent.updateResource(): error updating");
     console.error(bad);
   })
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

 showLocation(id){
  this.locationSvc.showLocation((this.currentRoute.snapshot.paramMap.get('id'))).subscribe(
    good => {
      this.loadLocations();
    },
    bad => {
      console.error("Location.show(): error")
    }
  )
};

displayLocationTable(){
  this.selectedLocation = null;
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
createLocation(location: Location){
  console.log(location);
  this.locationSvc.createLocation(location).subscribe(
    good => {
      this.loadLocations();
      this.newLocation = new Location();
    },
    bad => {
      console.error("LocationComponent.createLocation(): error adding");
      console.error(bad);
    })
}

setEditLocation(){
  this.editLocation = Object.assign({}, this.selectedLocation)
}

updateLocation(location: Location){
  console.log(location);
 this.locationSvc.updateLocation(location).subscribe(
   good =>{
     this.loadLocations();
     this.editLocation = null;
     this.selectedLocation = this.editLocation;
   },
   bad => {
     console.error("LocationComponent.updateLocation(): error updating");
     console.error(bad);
   })
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



}
