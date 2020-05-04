import { Usergroup } from './../models/usergroup';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsergroupService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/usergroups';

  constructor(private http: HttpClient, private authService: AuthService) { }

  index() {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
      return this.http.get<Usergroup[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in usergroup service failed');
        })
        );
      }
    else{
      console.log("not logged in")
      //this.router.navigateByUrl('/login')
    }
  }

  public show(id: number){
    const httpOptions = this.getHttpOptions();
    console.log("Show usergroup with id "+id);
    if (this.authService.checkLogin()){
      return this.http.get<Usergroup>(`${this.url}/${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show single id in usergroup service failed');
        })
      );
    }
  }

  public create(usergroup: Usergroup){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
      return this.http.post<Usergroup>(this.url, usergroup, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('create method in usergroup service failed');
          })
        );
    }
  }


  public joinGroup(id: number){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
      return this.http.post<Usergroup>(`${this.url}/${id}/joingroup`, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('joinGroup method in usergroup service failed');
          })
        );
    }
  }

  public update(usergroup: Usergroup){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.put<Usergroup>(`${this.url}/${usergroup.id}`, usergroup, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('update method in usergroup service failed');
        })
      );
    }
  }

  public destroy(id: number){
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
        return this.http.delete<Usergroup>(`${this.url}/${id}`, httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('delete method in usergroup service failed');
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
}
