import { Injectable } from '@angular/core';
import  { Observable } from 'rxjs';
import {HttpClient,HttpParams,HttpHeaders} from '@angular/common/http';
import {UserClass} from '../classes/UserClass';
import {LoginClass} from '../classes/LoginClass';

const headerOption = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': 'Basic ' + btoa(sessionStorage.getItem('credentials'))
                      })
      };

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly url="http://localhost:9000/sacchon/users";

  readonly urlGetUserdata="http://localhost:9000/sacchon/profile";
  
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


  currentLogin: LoginClass = {
    
    
    userEmail: '',
    userPassword: ''
    
  }




  constructor(private http: HttpClient) { }


  registerUser(user: UserClass): Observable<UserClass> {
    return this.http.put<UserClass>(this.url, user, headerOption);
  }


  loginUser(user:LoginClass):Observable<any>{
    return this.http.post(this.url,user,headerOption);
  }

  getUserData():Observable<any>{
    return this.http.get(this.urlGetUserdata,headerOption);
  }
  editUserData(user:UserClass):Observable<UserClass>{​​​​​​​​ 
    return this.http.put<UserClass>(this.urlGetUserdata,user,headerOption);
}​​​​​​​​

}