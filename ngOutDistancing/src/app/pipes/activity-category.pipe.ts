import { Pipe, PipeTransform } from '@angular/core';
import { Activity } from '../models/activity';
import { Category } from '../models/category';

@Pipe({
  name: 'activityCategory'
})
export class ActivityCategoryPipe implements PipeTransform {

  transform(activities: Activity[], category: Category) {
    console.log(activities);
    console.log(category);

    const results = [];

    if(category.type ==='All'){
      return activities;
    }

    activities.forEach((activity) => {
      console.log(activity.categories);

      activity.categories.forEach((activityCategory) => {
        if(activityCategory.type === category.type) {
          results.push(activity);
        }
      });
    });

    return results;
  }

}
