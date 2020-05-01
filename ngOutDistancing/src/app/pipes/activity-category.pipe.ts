import { Pipe, PipeTransform } from '@angular/core';
import { Activity } from '../models/activity';

@Pipe({
  name: 'activityCategory'
})
export class ActivityCategoryPipe implements PipeTransform {

  transform(activityList: Activity[], category: string): unknown {
    const results = [];

    if(category ==='all'){
      return activityList;
    }

  //   activityList.forEach((activity) => {
  //     activity.categories.forEach((pokeType) => {
  //       if(pokeType.name === type) {
  //         results.push(poke);
  //       }
  //     });
  //   });

  //   return results;
  }

}
