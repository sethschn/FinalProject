import { Activity } from './../models/activity';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ActivityComment } from '../models/activity-comment';


@Injectable({
  providedIn: 'root'
})
export class ActivityCommentService {

  constructor(
    private http : HttpClient,
    private auth : AuthService
  ) { }

  private url = environment.baseUrl + 'api/';

  index() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<ActivityComment[]>(this.url + '?sorted=true',httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in activity comment service failed');
        })
      );
  }

  create(data: ActivityComment, activityId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<ActivityComment>(this.url + 'activities/' + `${activityId}` + '/activitycomments', data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in activity comment service failed');
      })
    );
  }

  show(id:number){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    console.log("show todo with id: "+ id);
    return this.http.get<ActivityComment>(`${this.url}/` + 'activitycomments/' + `${id}`,httpOptions)
    .pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError('show single method in activity comment service failed');
    })
    )
  }

  update(data: ActivityComment, activityId: number, commentId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put<ActivityComment>(this.url + 'activities/' + `${activityId}` + '/activitycomments'+ `${commentId}`, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update method in activity comment service failed');
      })
    );
  }


}
