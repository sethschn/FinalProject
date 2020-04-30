import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private url = environment.baseUrl + 'api/events'

  private events : Event [] = [];

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({

        'Content-Type': 'application/json',
        'Athorization': 'my-auth-token'
      })
    };
    return httpOptions;
  }

  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Event[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EventService.index: error retrieving events: ' + err);
      })
    );
  }

  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Event>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EventService.show: error retrieving event: ' + err);
      })
    );
  }
}
