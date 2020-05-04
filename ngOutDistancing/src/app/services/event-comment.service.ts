import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Eventcomment } from '../models/eventcomment';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EventCommentService {

  constructor(
    private http : HttpClient,
    private auth : AuthService
  ) { }

  private url = environment.baseUrl + 'api/';

  index(activityId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Eventcomment[]>(this.url + `activities/${activityId}/activitycomments`,httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in activity comment service failed');
        })
      );
  }

  createComment(data: Eventcomment, activityId: number) {
    const httpOptions = this.getHttpOptions();
    console.log(data);
    console.log(activityId);
    if (this.auth.checkLogin()){
    return this.http.post<Eventcomment>(this.url + `activities/${activityId}/activitycomments`, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in activity comment service failed');
      })
    );
    }
  }


  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${this.auth.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return httpOptions;
  }







}
