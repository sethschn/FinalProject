import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-dynamic-markers',
  templateUrl: './dynamic-markers.component.html',
  styleUrls: ['./dynamic-markers.component.css']
})
export class DynamicMarkersComponent implements OnInit {
  title = 'angular-gmap'
    @ViewChild('mapContainer', {static: false}) gmap: ElementRef;
    map: google.maps.Map;
    lat = 39.742043
    lng = -104.991531

    coordinates = new google.maps.LatLng(this.lat, this.lng);

    mapOptions: google.maps.MapOptions = {
      center: this.coordinates,
      zoom: 10,
    };

    marker = new google.maps.Marker({
      position: this.coordinates,
      map: this.map,
    });

    markers = [
      {
        position: new google.maps.LatLng(39.6088, -104.9027),
        map: this.map,
        title: "Skill Distillery"
      },
      {
        position: new google.maps.LatLng(39.5883, -105.6438),
        map: this.map,
        title: "Mount Evans"
      }
    ];

longitude = 20.728218;
latitude = 52.128973;

// markers = [
// { latitude: 52.228973, longitude: 20.728218 }
// ];

placeMarker(position: any) {
const lat = position.coords.lat;
const lng = position.coords.lng;

// this.markers.push({ latitude: lat, longitude: lng });
}

  constructor() { }

  ngAfterViewInit(): void {
    this.mapInitializer();
  }
  loadAllMarkers(): void {
    this.markers.forEach(markerInfo => {

      const marker = new google.maps.Marker({
        ...markerInfo
      });

      const infoWindow = new google.maps.InfoWindow({
        content: marker.getTitle()
      });

      marker.addListener("click", () => {
        infoWindow.open(marker.getMap(), marker);
      });

      marker.setMap(this.map);
    });
  }

  mapInitializer(): void {
    this.map = new google.maps.Map(this.gmap.nativeElement, this.mapOptions);

    this.marker.addListener("click", () => {
      const infoWindow = new google.maps.InfoWindow({
        content: this.marker.getTitle()
      });
      infoWindow.open(this.marker.getMap(), this.marker);
    });

    this.marker.setMap(this.map);

    this.loadAllMarkers();
  }

  ngOnInit(): void {
    this.mapInitializer();
  }

}
