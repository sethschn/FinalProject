import { EventCommentService } from './../../services/event-comment.service';
import { Event } from './../../models/event';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Eventcomment } from 'src/app/models/eventcomment';


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


  constructor(
    private eventSvc: EventService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private commentSvc: EventCommentService
  ) {}

  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.eventSvc
        .show(this.currentRoute.snapshot.paramMap.get('id'))
        .subscribe(
          (data) => {
            this.selected = data;
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

  displayEvent(event) {
    this.router.navigateByUrl(`/event/${event.id}`);
  }

  showEvent(id) {
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

  setEditEvent() {
    this.editCurrentEvent = Object.assign({}, this.selected);
  }

  updateCurrentEvent(event: Event) {
    console.log(event);
    this.eventSvc.update(event).subscribe(
      good => {
        this.loadEvents();
        // this.editEvent = null;
        this.selected = this.editCurrentEvent;
      },
      bad => {
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
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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

  addComment(comment: Eventcomment){
    this.commentSvc.createComment(comment, this.currentEventId).subscribe(
      good => {
        this.newComment= new Eventcomment();
        // this.selected = null;
        console.log(this.currentEventId);
        // this.router.navigateByUrl(`/activities/${this.currentActivityId}`);
        this.loadComments();
      },
      bad =>{
        console.error('activity.component.ts addComment(): error adding comment')
        console.error(bad);
      }
    );
  }

  loadComments(){
    this.commentSvc.index(this.selected.id).subscribe(
      comments =>{
        this.eventComments = comments;
        console.log(comments);
      },
      fail => {
        console.error(fail);
        // this.router.navigateByUrl(`/notFound`)
      }
    );


  }


}
