import { Event } from './../models/event';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { EventService } from './event.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/users';


  events: Event[]= [];

  constructor(private http: HttpClient, private eventSvc: EventService, private authService: AuthService) { }

  index() {
    const httpOptions = this.getHttpOptions();
      return this.http.get<User[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in user service failed');
        })
        );
      }

  public show(id: number){
    const httpOptions = this.getHttpOptions();
    console.log("Show user with id "+id);
      return this.http.get<User>(`${this.url}/${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show single id in user service failed');
        })
      );
    }

    public showLoggedInUser(){
      const id = this.authService.getCurrentUserId();
      const httpOptions = this.getHttpOptions();
      console.log("ShowLoggedInUser "+id);
        return this.http.get<User>(`${this.url}/${id}`, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('show logged in user in user service failed');
          })
        );
      }

  public update(user: User){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.put<User>(`${this.url}/${user.id}`, user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('update method in user service failed');
        })
      );
    }
  }

  public disableUser(id: number){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
        return this.http.delete<User>(`${this.url}/${id}`, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('delete method in user service failed');
          })
        );
      }
    }

  private getHttpOptions(){
    const credentials = this.authService.getCredentials();
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }

  private showUserEvents(){
    const httpOptions = this.getHttpOptions();
      return this.http.get<Event[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in user service failed');
        })
        );
      }

  public addUserEvent(event, user){
    const httpOptions = this.getHttpOptions();
    return this.http.put<User>(`${this.url}/${user.id}/events/${event.id}`, {}, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('addUserEvent method in user service failed');
      })
      );
  }

  public removeUserEvent(event, user){
    const httpOptions = this.getHttpOptions();
    return this.http.delete<User>(`${this.url}/${user.id}/events/${event.id}`, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('removeUserEvent method in user service failed');
      })
      );
  }


  }


