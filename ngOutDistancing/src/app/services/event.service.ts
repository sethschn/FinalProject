import { Event } from './../models/event';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Activity } from '../models/activity';


@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api'
  activity = new Activity();




  constructor(private http: HttpClient, private auth: AuthService) { }

  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${this.auth.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }

  public index() {
    return this.http.get<Event[]>(this.url + '/events').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EventService.index: error retrieving events: ' + err);
      })
    );
  }

  public show(id) {
    return this.http.get<Event>(`${this.url}/events/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EventService.show: error retrieving event: ' + err);
      })
    );
  }
  public create(event: Event, activity: Activity){
    const httpOptions = this.getHttpOptions();
    console.log(event);
    if (this.auth.checkLogin()){
      return this.http.post<Event>(`${this.url}/activities/${activity.id}/events`, event, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('create method in Event service failed');
          })
        );
    }
  }

  public update(event: Event){
    const httpOptions = this.getHttpOptions();
    if (this.auth.checkLogin()){
    return this.http.put<Event>(`${this.url}/activities/${event.activity.id}/events/${event.id}`, event, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('update method in Event service failed');
        })
      );
    }
  }

  public destroy(id: number){
    const httpOptions = this.getHttpOptions();
    console.log(typeof id);
    console.log(id);

    if (this.auth.checkLogin()){
        return this.http.delete<Event>(`${this.url}/events/${id}`, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('delete method in Event service failed');
          })
        );
      }
    }
}
