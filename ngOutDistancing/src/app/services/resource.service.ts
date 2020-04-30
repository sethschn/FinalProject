import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Resource } from '../models/resource';
import { AuthService } from './auth.service';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/resources';
  constructor(
    private http: HttpClient, private datePipe: DatePipe, private authService: AuthService
  ) { }


  public indexResource() {
    return this.http.get<Resource[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index is not working in resource');
        })
      );
  }

  public showResource(id) {
    return this.http.get<Resource>(`${this.url}/${id}`).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show method in resource service failed');
        })
      );
  }

  public createResource(resource: Resource) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.post<Resource>(this.url, resource, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in resource service failed');
      })
    );
    }
  }
  public updateResource(resource: Resource) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.put<Resource>(`${this.url}/${resource.id}`, resource, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update method in resource service failed');
      })
      );
    }
  }
  public deleteResource(id: number) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.delete<Resource>(`${this.url}/${id}`, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error deleting resource');
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


