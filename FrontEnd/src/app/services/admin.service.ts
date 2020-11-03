import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DoctorInactive } from '../classes/doctoInactive';
import { IsDoctor } from '../classes/isDoctor';
import { PatientInactive } from '../classes/patientInactive';
import { PendingDoctor } from '../classes/pendingDoctor';
import { PostData } from '../classes/postData';
import { SubData } from '../classes/subData';
import { SubPatient } from '../classes/subPatient';

const headerOption = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': 'Basic ' + btoa(sessionStorage.getItem('credentials'))
                      })
      };

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readonly urlDoctor="http://localhost:9000/sacchon/expired?needDoctors=true";
  readonly urlPatient="http://localhost:9000/sacchon/expired?needDoctors=false";
  readonly urlSubPatient="http://localhost:9000/sacchon/measurements";
  readonly PendingDoctor="http://localhost:9000/sacchon/pending";

  constructor(private http: HttpClient) { }

  doctorInactive: DoctorInactive = {
    
    doctorID: 0,
    lastLogin: '',
    amka:0
  }
  patientInactive: PatientInactive = {
    doctorID: 0,
    lastLogin: '',
    amka:0 
  }
  subPatient: SubPatient = {
    bloodGlucoseLevel: 0,
    carbIntake: 0,
    measurementDate:''
   
  }
  subData: SubData = { 
    amka:0,
    startAt:'',
    endAt:''
   
  }
  pending:PendingDoctor={
    
    id: 0,
    first_name: '',
    last_name: '',
    email: '',
    accountType: '',
    amka: 0,
    registration_date: '',
}

  beDoctor: IsDoctor={
  userID:0
}

  getInactiveDoctor():Observable<any>{
    return this.http.get<DoctorInactive>(this.urlDoctor,headerOption);
  }
  getInactivePatient():Observable<any>{
    return this.http.get<DoctorInactive>(this.urlPatient,headerOption);
  }
  getSubPatient(data:SubData):Observable<any>{
    return this.http.patch<SubData>(this.urlSubPatient, data,headerOption);
}
  getPendingDoctor():Observable<any>{
  let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
    .set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
  }
  return this.http.get(this.PendingDoctor, header)
}
    getBeDoctor(data: IsDoctor):Observable<any>{
  let header= { headers:new HttpHeaders().set('Content-Type', 'application/json')
  .set('Authorization',`Basic ${btoa(sessionStorage.getItem("credentials"))}`)
}
return this.http.post<IsDoctor>(this.PendingDoctor, data,header);
}

}
