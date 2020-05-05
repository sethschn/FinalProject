import { ActivityService } from './../../services/activity.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { ActivityComment } from 'src/app/models/activity-comment';
import { ActivityCommentService } from 'src/app/services/activity-comment.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';
import { EventService } from 'src/app/services/event.service';
import { Event } from './../../models/event';
import { Location } from './../../models/location';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css'],
})
export class ActivityComponent implements OnInit {
  activity = new Activity();
  activities: Activity[] = [];
  activityComments: ActivityComment[] = [];

  categories: Category[] = [];
  selectedCategory: Category = new Category(0, 'All');

  eventList: Event[] = [];
  newEvent = new Event();
  newLocation = new Location();

  user = new User();

  closeResult = '';
  comment = new ActivityComment();
  currentActivityId = null;
  editCurrentActivity = null;
  newActivity = new Activity();
  newComment = new ActivityComment();
  selected = null;

  constructor(
    private activitySvc: ActivityService,
    private commentSvc: ActivityCommentService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private catSvc: CategoryService,
    private eventSvc: EventService,
    private authSvc: AuthService,
    private userSvc: UserService
  ) {}

  ngOnInit(): void {
    const activityIdStr = this.currentRoute.snapshot.paramMap.get('id');
    console.log('selected:' + this.selected);
    console.log('activityIdStr: ' + activityIdStr);
    this.loadCategories();
    if (!this.selected && activityIdStr) {
      this.currentActivityId = activityIdStr;
      const activityId = Number.parseInt(activityIdStr, 10);
      this.activitySvc.show(activityId).subscribe(
        (activity) => {
          this.selected = activity;
          console.log(this.selected);
          this.loadComments();
        },
        (fail) => {
          console.error(fail);
          this.router.navigateByUrl(`/notFound`);
        }
      );
    } else {
      this.reload();
    }
  }

  loadComments() {
    this.commentSvc.index(this.selected.id).subscribe(
      (comments) => {
        this.activityComments = comments;
        console.log(comments);
      },
      (fail) => {
        console.error(fail);
        // this.router.navigateByUrl(`/notFound`)
      }
    );
  }

  reload() {
    this.activitySvc.index().subscribe(
      (data) => {
        this.activities = data;
      },
      (bad) => {
        console.error(
          'nonUserLanding.reload(): error retrieving activity list'
        );
        console.error(bad);
      }
    );
  }

  loadActivities() {
    this.activitySvc.index().subscribe(
      (data) => {
        this.activities = data;
      },
      (err) => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

  addNewActivity(activity: Activity) {
    this.activitySvc.create(activity).subscribe(
      (good) => {
        this.reload();
        this.newActivity = new Activity();
      },
      (bad) => {
        console.error(
          'workoutListComponent.addWorkout(): error adding workout'
        );
        console.error(bad);
      }
    );
  }

  updateCurrentActivity(activity: Activity) {
    console.log('update activity: ' + activity);
    this.activitySvc.update(activity).subscribe(
      (yay) => {
        this.reload();
        // this.editCurrentActivity = null;
        this.selected = this.editCurrentActivity;
      },
      (boo) => {
        console.error('activity component updating activity error');
      }
    );
  }

  setEditActivity() {
    this.editCurrentActivity = Object.assign({}, this.selected);
  }

  displayTable(activities) {
    this.selected = null;
    this.router.navigateByUrl(`/activities`);
  }

  displayActivity(activity) {
    console.log('--------------------');
    console.log(activity);
    this.router.navigateByUrl(`/activities/${activity.id}`);
  }

  addComment(comment: ActivityComment) {
    this.commentSvc.createComment(comment, this.currentActivityId).subscribe(
      (good) => {
        this.newComment = new ActivityComment();
        // this.selected = null;
        console.log(this.currentActivityId);
        // this.router.navigateByUrl(`/activities/${this.currentActivityId}`);
        this.loadComments();
      },
      (bad) => {
        console.error(
          'activity.component.ts addComment(): error adding comment'
        );
        console.error(bad);
      }
    );
  }

  loadCategories() {
    this.catSvc.indexCategory().subscribe(
      (data) => {
        const cat = new Category();
        cat.type = 'All';
        this.selectedCategory = cat;
        this.categories = data;
        this.categories.unshift(cat);
      },
      (err) => {
        console.error('Error in our loadActivities() method. ' + err);
      }
    );
  }

  addNewEvent(event: Event, activity: Activity, newLocation: Location) {
    console.log(event);
    event.location = newLocation;
    this.eventSvc.create(event, activity).subscribe(
      (good) => {
        // this.loadEvents();
        this.newEvent = new Event();
        this.router.navigateByUrl(`/eventDetail`);
      },
      (bad) => {
        console.error('EventDetailListComponent.createEvent(): error adding');
        console.error(bad);
      }
    );
  }

  loadEvents() {
    this.eventSvc.index().subscribe(
      (data) => {
        this.eventList = data;
      },
      (bad) => {
        console.error('EventDetailComponent.loadEvents(): error loading');
        console.error(bad);
      }
    );
  }

  checkIfAdmin(user: User) {
    if (this.authSvc.getCurrentUserRole() == 'admin') {
      return true;
    } else {
      return false;
    }
  }

  checkIfLoggedIn() {
    return this.authSvc.checkLogin();
  }

  // Opens Modal
  open(content) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  // Close Modal
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
