import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Activity } from '../models/activity';


@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  // private activities: Activity[] = [];

  constructor(
    private auth : AuthService,
    private http : HttpClient
  ) { }

  private url = environment.baseUrl + 'api/activities';

  index() {
    return this.http.get<Activity[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in activity service failed');
        })
      );
  }

  create(data: Activity) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<Activity>(this.url, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in activity service failed');
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
    return this.http.get<Activity>(`${this.url}/${id}`)
    .pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError('show single method in activity service failed');
    })
    )
  }

  update(data: Activity, todoId : number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Basic ` + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put<Activity>(this.url +'/'+ todoId, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update method in activity service failed');
      })
    );
  }







}
