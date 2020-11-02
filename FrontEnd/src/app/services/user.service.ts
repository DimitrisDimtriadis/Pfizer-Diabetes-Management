import { Injectable } from '@angular/core';
import  { Observable } from 'rxjs';
import {HttpClient,HttpParams,HttpHeaders} from '@angular/common/http';
import {UserClass} from '../classes/UserClass';
import {LoginClass} from '../classes/LoginClass';
import { AverageDataPatient } from '../classes/averageDataPatient';
import { AverageMeasurements } from '../classes/averageMeasurements';

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

  readonly urlI="http://localhost:9000/sacchon/users/interacts";

  readonly average="http://localhost:9000/sacchon/data";
  
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

  averageData: AverageDataPatient = { 
    amka:0,
    startAt:'',
    endAt:''
  }

  //LISTA
  averageDataMeasurements: AverageMeasurements={
    //patientID: 0,
    numbersOfData: 0,
    avgBloodGlucoseLevel: 0,
    avgCarbIntakeAVG: 0

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


  deleteUser():Observable<UserClass>{
    let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
    .set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
  }
  return this.http.delete<UserClass>(this.urlI ,header);
  }

  averageDataPatient(data: AverageDataPatient):Observable<any>{
    let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
  }
  return this.http.post<AverageDataPatient>(this.average,data, header);
}
}
