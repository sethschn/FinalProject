import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from '../models/category';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/categories';
  constructor(
    private http: HttpClient, private authService: AuthService
  ) { }

  public indexCategory() {
    return this.http.get<Category[]>(this.url + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('index is not working in category');
        })
      );
  }

  public showCategory(id) {
    return this.http.get<Category>(`${this.url}/${id}`).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('show method in category service failed');
        })
      );
  }

  public createCategory(category: Category) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.post<Category>(this.url, category, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create method in category service failed');
      })
    );
    }
  }
  public updateCategory(category: Category) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.put<Category>(`${this.url}/${category.id}`, category, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update method in category service failed');
      })
      );
    }
  }
  public deleteCategory(id: number) {
    const httpOptions = this.getHttpOptions();
    if (this.authService.checkLogin()){
    return this.http.delete<Category>(`${this.url}/${id}`, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error deleting category');
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
