import { Pipe, PipeTransform } from '@angular/core';
import { Event } from '../models/event';

@Pipe({
  name: 'event'
})
export class EventPipe implements PipeTransform {

  transform(events: Event[], showDisabled: boolean = false) {

    const results = [];

    events.forEach((event) => {
      if(event.enabled){
        results.push(event);
      }
    });

    return results;
  }

}
