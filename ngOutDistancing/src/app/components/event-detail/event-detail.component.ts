import { UserService } from './../../services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventCommentService } from './../../services/event-comment.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Eventcomment } from 'src/app/models/eventcomment';
import { Activity } from 'src/app/models/activity';
import { User } from 'src/app/models/user';
import { Event } from 'src/app/models/event';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css'],
})
export class EventDetailComponent implements OnInit {
  closeResult = '';
  selected = null;
  newEvent = new Event();
  comment = new Eventcomment();
  editEvent = null;
  editCurrentEvent = null;
  eventComments: Eventcomment[] = [];
  eventList: Event[] = [];
  newComment = new Eventcomment();
  currentEventId = null;
  event = new Event();
  eventActivity = new Activity();
  user = new User();

  constructor(
    private eventSvc: EventService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private commentSvc: EventCommentService,
    private authSvc: AuthService,
    private userSvc: UserService
  ) {}

  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.currentEventId = this.currentRoute.snapshot.paramMap.get('id');
      this.eventSvc
        .show(this.currentRoute.snapshot.paramMap.get('id'))
        .subscribe(
          (data) => {
            this.selected = data;
            this.loadComments();
          },
          (bad) => {
            this.router.navigateByUrl('eventDetail');
            console.error(
              'EventDetailComponent.reload(): error retrieving event detail list'
            );
            console.error(bad);
          }
        );
    }
    this.loadEvents();
  }

  displayAllEvents() {
    this.router.navigateByUrl(`/eventDetail`);
  }
  displayEvent(event) {
    this.router.navigateByUrl(`/event/${event.id}`);
  }

  showEvent(event) {
    console.log(event);
    this.eventSvc.show(this.currentRoute.snapshot.paramMap.get('id')).subscribe(
      (good) => {
        this.loadEvents();
      },
      (bad) => {
        console.error('EventDetailListComponent.show(): error');
      }
    );
  }

  displayTable() {
    this.selected = null;
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

  // addNewEvent(event: Event) {
  //   console.log(event);
  //   this.eventSvc.create(event).subscribe(
  //     (good) => {
  //       this.loadEvents();
  //       this.newEvent = new Event();
  //     },
  //     (bad) => {
  //       console.error('EventDetailListComponent.createEvent(): error adding');
  //       console.error(bad);
  //     }
  //   );
  // }

  // saveEventToUser(event) {
  //   console.log(event);
  //   const user = this.userSvc.showLoggedInUser();
  //   this.userSvc.addUserEvent(event, user).subscribe(
  //     good => {
  //       this.loadEvents();
  //     },
  //     bad => {
  //       console.error('EventDetailComponent.saveEventToUser(): error saving')
  //     }
  //   )
  // }

  saveEventToUser(event) {
    this.userSvc.showLoggedInUser().subscribe(
      (good) => {
        const user = good;
        this.userSvc.addUserEvent(event, user).subscribe(
          (success) => {
            this.router.navigateByUrl(`/profile`);
          },
          (fail) => {
            console.error(
              'EventDetailComponent.saveEventToUser(): error saving'
            );
          }
        );
      },
      (bad) => {
        console.error('EventDetailComponent.saveEventToUser(): error saving');
      }
    );
  }

  removeEventFromUser(event) {
    this.userSvc.showLoggedInUser().subscribe(
      (good) => {
        const user = good;
        this.userSvc.removeUserEvent(event, user).subscribe(
          (success) => {
            this.router.navigateByUrl(`/profile`);
          },
          (fail) => {
            console.error(
              'EventDetailComponent.removeEventToUser(): error saving'
            );
          }
        );
      },
      (bad) => {
        console.error('EventDetailComponent.removeEventToUser(): error saving');
      }
    );
  }

  setEditEvent() {
    this.editCurrentEvent = Object.assign({}, this.selected);
  }

  checkIfCreator(event){
    const userId = this.authSvc.getCurrentUserId();
    console.log(userId);
    console.log(event.creator.id);
    if (event.creator.id == userId){
      return true;
    } else {
      return false;
    }
  }

  updateCurrentEvent(event: Event) {
    console.log(event);
    this.eventSvc.update(event).subscribe(
      (good) => {
        this.loadEvents();
        // this.editEvent = null;
        this.selected = this.editCurrentEvent;
      },
      (bad) => {
        console.error('EventDetailListComponent.updateEvent(): error updating');
        console.error(bad);
      }
    );
  }

  deleteEvent(eventId) {
    this.eventSvc.destroy(eventId).subscribe(
      (data) => {
        this.loadEvents();
        this.selected = null;
      },
      (err) => {
        console.error(
          'EventDetailListComponent.deleteEvent(): error deleting' + err
        );
      }
    );
  }

  // open modal
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
  // close modal
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  addEventComment(comment: Eventcomment) {
    this.commentSvc
      .createComment(comment, this.selected.activity.id, this.currentEventId)
      .subscribe(
        (good) => {
          this.newComment = new Eventcomment();
          console.log(this.currentEventId);
          this.loadComments();
        },
        (bad) => {
          console.error(
            'event-detail.component.ts addComment(): error adding comment'
          );
          console.error(bad);
        }
      );
  }

  loadComments() {
    this.commentSvc
      .index(this.selected.activity.id, this.selected.id)
      .subscribe(
        (comments) => {
          this.eventComments = comments;
          console.log(comments);
        },
        (fail) => {
          console.error(fail);
          // this.router.navigateByUrl(`/notFound`)
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

  deleteComment(eventComment: Eventcomment, event: Event) {
    this.commentSvc.disableEventComment(event, eventComment).subscribe(
      (data) => {
        this.loadComments();
      },
      (err) => {
        console.error('error in deleting activity component');
      }
    );
  }
}
