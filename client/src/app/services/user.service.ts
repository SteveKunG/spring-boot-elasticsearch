import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getUserById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/id/${id}`);
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  updateUser(id: number, user: Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}/id/${id}`, user);
  }

  deleteUserById(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/id/${id}`);
  }
}