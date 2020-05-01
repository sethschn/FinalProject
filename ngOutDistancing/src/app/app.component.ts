import { Component, AfterViewInit, ViewChild, ElementRef} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements AfterViewInit{
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
  constructor(){

  }

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
}
