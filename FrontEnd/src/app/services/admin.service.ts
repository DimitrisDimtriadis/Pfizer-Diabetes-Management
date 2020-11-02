import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DoctorInactive } from '../classes/doctoInactive';
import { PatientInactive } from '../classes/patientInactive';
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

  getInactiveDoctor():Observable<any>{
    return this.http.get<DoctorInactive>(this.urlDoctor,headerOption);
  }
  getInactivePatient():Observable<any>{
    return this.http.get<DoctorInactive>(this.urlPatient,headerOption);
  }
  getSubPatient(data:SubData):Observable<any>{
    return this.http.patch<SubData>(this.urlSubPatient, data,headerOption);
}
}
