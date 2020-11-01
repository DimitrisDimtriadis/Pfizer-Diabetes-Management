import { Injectable } from '@angular/core';
import  { Observable } from 'rxjs';
import {HttpClient,HttpParams,HttpHeaders} from '@angular/common/http';
import {UserClass} from '../classes/UserClass';
import {LoginClass} from '../classes/LoginClass';

//const headerOption = {
 // headers1: new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': 'Basic ' + btoa(sessionStorage.getItem("credentials"))
  //                    })
  //    };

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
    let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
    //.set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
  }
    return this.http.put<UserClass>(this.url, user, header);
  }


  loginUser(user:LoginClass):Observable<any>{
    let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
   // .set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
  }
    return this.http.post(this.url,user,header);
  }

  getUserData():Observable<any>{
    let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
    .set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
  }
    return this.http.get(this.urlGetUserdata,header);
  }

  editUserData(user:UserClass):Observable<UserClass>{
    let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
    .set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
  }
    return this.http.put<UserClass>(this.urlGetUserdata,user,header);
  }
 

}
