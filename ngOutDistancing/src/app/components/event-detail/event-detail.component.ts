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


  selected = null;
  newEvent = new Event();

  events: Event[] = [];

  constructor(private eventSvc: EventService, private currentRoute: ActivatedRoute, private router: Router) { }


  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.eventSvc.show(this.currentRoute.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.selected = data;
        },
        bad => {
          this.router.navigateByUrl('home')
          console.error('EventDetailComponent.reload(): error retrieving event detail list');
          console.error(bad);
        }
        );
      }
      this.loadEvents();
    }


  loadEvents(){
    this.eventSvc.index().subscribe(
      data => {this.events = data;
      },
      bad => {
        console.error("GratitudeListComponent.loadEntries(): error loading");
        console.error(bad);
      }
    );
  }

}
