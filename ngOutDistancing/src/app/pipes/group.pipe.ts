import { Usergroup } from './../models/usergroup';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'group'
})
export class GroupPipe implements PipeTransform {

  transform(groups: Usergroup[]): unknown {
    const results = [];

    groups.forEach((group) => {
      if(group.enabled){
        results.push(group);
      }
    });

    return results;

  }

}
