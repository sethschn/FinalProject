import { Component, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-location-detail',
  templateUrl: './location-detail.component.html',
  styleUrls: ['./location-detail.component.css']
})
export class LocationDetailComponent implements OnInit {

  selected = null;
  newLocation = new Location();
  editLocation = null;
  locationList: Location[] = [];

  constructor(private locationSvc: LocationService, private currentRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.locationSvc.showLocation(this.currentRoute.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.selected = data;
        },
        bad => {
          this.router.navigateByUrl('locationDetail')
          console.error('EventDetailComponent.reload(): error retrieving event detail list');
          console.error(bad);
        }
        );
      }
      this.loadLocations();
  }

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

displayTable(){
  this.selected = null;
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
  this.editLocation = Object.assign({}, this.selected)
}

updateLocation(location: Location){
  console.log(location);
 this.locationSvc.updateLocation(location).subscribe(
   good =>{
     this.loadLocations();
     this.editLocation = null;
     this.selected = this.editLocation;
   },
   bad => {
     console.error("LocationComponent.updateLocation(): error updating");
     console.error(bad);
   })
}

deleteLocation(locationId){
  this.locationSvc.deleteLocation(locationId).subscribe(

    data => {
      this.loadLocations();
      this.selected = null;

    },
    err => {
      console.error("LocationDetailListComponent.deleteLocation(): error deleting" + err);
    })
  };


}
