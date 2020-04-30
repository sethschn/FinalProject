import { Event } from './../../models/event';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';


@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  title = 'Checking Event Details Page'
  selected = null;
  newEvent = new Event();
  editEvent = null;

  eventList: Event[] = [];

  constructor(private eventSvc: EventService, private currentRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.eventSvc.show(this.currentRoute.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.selected = data;
        },
        bad => {
          this.router.navigateByUrl('eventDetail')
          console.error('EventDetailComponent.reload(): error retrieving event detail list');
          console.error(bad);
        }
        );
      }
      this.loadEvents();
    }


  displayEvent(event){
     this.router.navigateByUrl(`/event/${event.id}`);
  }

  showEvent(id){
    this.eventSvc.show((this.currentRoute.snapshot.paramMap.get('id'))).subscribe(
      good => {
        this.loadEvents();
      },
      bad => {
        console.error("EventDetailListComponent.show(): error")
      }
    )
  };

  displayTable(){
    this.selected = null;
  }

  loadEvents(){
    this.eventSvc.index().subscribe(
      data => {this.eventList = data;
      },
      bad => {
        console.error("EventDetailComponent.loadEvents(): error loading");
        console.error(bad);
      }
    );
  }

  createEvent(event: Event){
    console.log(event);
    this.eventSvc.create(event).subscribe(
      good => {
        this.loadEvents();
        this.newEvent = new Event();
      },
      bad => {
        console.error("EventDetailListComponent.createEvent(): error adding");
        console.error(bad);
      })
  }

  setEditEvent(){
    this.editEvent = Object.assign({}, this.selected)
  }

  updateEvent(event: Event){
    console.log(event);
   this.eventSvc.update(event).subscribe(
     good =>{
       this.loadEvents();
       this.editEvent= null;
       this.selected = this.editEvent;
     },
     bad => {
       console.error("EventDetailListComponent.updateEvent(): error updating");
       console.error(bad);
     })
  }

  deleteEvent(eventId){
    this.eventSvc.destroy(eventId).subscribe(

      data => {
        this.loadEvents();
        this.selected = null;

      },
      err => {
        console.error("EventDetailListComponent.deleteEvent(): error deleting" + err);
      })
    };

  }
