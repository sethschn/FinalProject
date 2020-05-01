import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dynamic-markers',
  templateUrl: './dynamic-markers.component.html',
  styleUrls: ['./dynamic-markers.component.css']
})
export class DynamicMarkersComponent implements OnInit {

  longitude = 20.728218;
latitude = 52.128973;

markers = [
{ latitude: 52.228973, longitude: 20.728218 }
];

placeMarker(position: any) {
const lat = position.coords.lat;
const lng = position.coords.lng;

this.markers.push({ latitude: lat, longitude: lng });
}

  constructor() { }

  ngOnInit(): void {
  }

}
