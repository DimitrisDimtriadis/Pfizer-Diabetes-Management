import { Injectable } from '@angular/core';
import  { Observable } from 'rxjs';
import {HttpClient,HttpParams,HttpHeaders} from '@angular/common/http';
import {UserClass} from '../classes/UserClass';

const headerOption = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly url="http://localhost:9000/sacchon/users";
  
  currentUser: UserClass = {
    
    first_name:'',
    last_name: '',
    email: '',
    password: '',
    accountType: 0,
    amka: 0,
    mobile_phone_number: 0,
    address:'',
    gender: 0,
    phone_number:0,
    
  }

  constructor(private http: HttpClient) { }


  registerUser(user: UserClass): Observable<UserClass> {
    return this.http.put<UserClass>(this.url, user, headerOption);
  }
}
