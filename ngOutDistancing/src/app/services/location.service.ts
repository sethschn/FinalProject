import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Location } from '../models/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  private url = environment.baseUrl + 'api/locations';

  indexLocation() {
    return this.http.get<Location[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index is not working in location');
        })
      );
  }

  public showLocation(id: number) {
    return this.http.get<Location>(`${this.url}/${id}`).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show method in location service failed');
        })
      );
  }

  deleteLocation(id: number) {
    return this.http.delete<Location>(this.url + '/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error deleting resource');
      })
    );
  }

  createLocation(data: Location) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post<any>(this.url, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in location service failed');
      })
    );
  }

  public updateLocation(location: Location) {
    return this.http.put<Location>(`${this.url}/${location.id}`, location)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update method in location service failed');
      })
    );
  }
}
