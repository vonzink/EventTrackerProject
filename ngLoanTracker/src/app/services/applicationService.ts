import { Application } from '../models/application';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private url = environment.baseURL + 'api/applications';

   constructor(private http: HttpClient) { }

  index(): Observable<Application[]> {
    return this.http.get<Application[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error("❌ error retrieving applications: " + err.message));
      })
    );
  }

  getActiveApplications(): Observable<Application[]> {
    return this.http.get<Application[]>(`${this.url}/active`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error("❌error retrieving active applications: " + err.message));
      })
    );
  }

  searchByName(name: string): Observable<Application[]> {
    const params = new HttpParams().set('name', name);
    return this.http.get<Application[]>(`${this.url}/search/name`, { params }).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error("❌ error searching by name: " + err.message));
      })
    );
  }

  searchByAddress(address: string): Observable<Application[]> {
    const params = new HttpParams().set('address', address);
    return this.http.get<Application[]>(`${this.url}/search/address`, { params }).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error("❌ error searching by address: " + err.message));
      })
    );
  }
searchByDateRange(from: string, to: string): Observable<Application[]> {
  const params = new HttpParams()
    .set('from', from)
    .set('to', to);
  return this.http.get<Application[]>(`${this.url}/search/date`, { params }).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(() => new Error("❌ error searching by date: " + err.message));
    })
  );
}

searchByLoanNumber(loanNumber: string): Observable<Application[]> {
  const params = new HttpParams().set('loanNumber', loanNumber);
  return this.http.get<Application[]>(`${this.url}/search/loanNumber`, { params }).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(() => new Error("❌ error searching by loan number: " + err.message));
    })
  );
}

  searchByStatus(status: string): Observable<Application[]> {
    const params = new HttpParams().set('status', status);
    return this.http.get<Application[]>(`${this.url}/search/status`, { params }).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error("❌ error searching by status: " + err.message));
      })
    );
  }
   // Existing create method:
  newLoan(app: Application): Observable<Application> {
  return this.http.post<Application>(this.url, app);
}

updateLoan(app: Application): Observable<Application> {
  return this.http.put<Application>(`${this.url}/${app.id}`, app);
}
deleteLoan(id: number): Observable<void> {
  return this.http.delete<void>(`${this.url}/${id}`);
}
}

