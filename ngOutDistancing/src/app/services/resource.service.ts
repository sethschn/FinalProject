import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Resource } from '../models/resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }


private url = environment.baseUrl + 'api/resources';

  index() {
    return this.http.get<Resource[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index is not working in resource');
        })
      );
  }

  public show(id) {
    return this.http.get<Resource>(`${this.url}/${id}`).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show method in resource service failed');
        })
      );
  }

  delete(id: number) {
    return this.http.delete<Resource>(this.url + '/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error deleting resource');
      })
    );
  }
  create(data: Resource) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post<any>(this.url, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in resource service failed');
      })
    );
  }
  public update(resource: Resource) {
    return this.http.put<Resource>(`${this.url}/${resource.id}`, resource)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update method in resource service failed');
      })
    );
  }
}


