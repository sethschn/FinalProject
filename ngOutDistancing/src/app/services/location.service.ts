import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Location } from '../models/location';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/locations';

  constructor(
    private http: HttpClient, private authService: AuthService
  ) { }


  public indexLocation() {
    return this.http.get<Location[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index is not working in location');
        })
      );
  }

  public showLocation(id) {
    return this.http.get<Location>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('show method in location service failed');
      })
    );
  }


  public createLocation(location: Location) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.post<Location>(this.url, location, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('create method in location service failed');
        })
      );
    }
  }

  public updateLocation(location: Location) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.put<Location>(`${this.url}/${location.id}`, location, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('update method in location service failed');
        })
      );
    }
  }

  public deleteLocation(id: number) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.delete<Location>(`${this.url}/${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error deleting location');
        })
      );
    }
  }

  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${this.authService.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }
}
