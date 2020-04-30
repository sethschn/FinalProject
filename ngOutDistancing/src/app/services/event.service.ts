import { Event } from './../models/event';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/events'



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
    return this.http.get<Event[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EventService.index: error retrieving events: ' + err);
      })
    );
  }

  public show(id) {
    return this.http.get<Event>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EventService.show: error retrieving event: ' + err);
      })
    );
  }
  public create(event: Event){
    const httpOptions = this.getHttpOptions();
    if (this.auth.checkLogin()){
      return this.http.post<Event>(this.url, event, httpOptions)
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
    return this.http.put<Event>(`${this.url}/${event.id}`, event, httpOptions)
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
    if (this.auth.checkLogin()){
        return this.http.delete<Event>(`${this.url}/${id}`, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('delete method in Event service failed');
          })
        );
      }
    }
}
