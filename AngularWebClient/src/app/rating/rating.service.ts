import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rating } from './rating';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private url: string = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Rating[]> {
    let headers = new HttpHeaders().set('Authorization', 'Bearer f7762f79-f78a-4720-98cf-2ea5a4403c83');
    return this.http.get<Rating[]>(this.url, { headers: headers });
  }
}