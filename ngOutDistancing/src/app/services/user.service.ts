import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/user';

  constructor(private http: HttpClient, private datePipe: DatePipe, private authService: AuthService) { }

  index() {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
      return this.http.get<User[]>(this.url, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index method in todo service failed');
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
    console.log("Show todo with id "+id);
    if (this.authService.checkLogin()){
      return this.http.get<User>(`${this.url}/${id}`, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show single id in todo service failed');
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
