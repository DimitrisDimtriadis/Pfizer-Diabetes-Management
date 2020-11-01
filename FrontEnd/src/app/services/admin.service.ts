import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DoctorInactive } from '../classes/doctoInactive';
import { PatientInactive } from '../classes/patientInactive';

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
  getInactiveDoctor():Observable<any>{
    return this.http.get<DoctorInactive>(this.urlDoctor,headerOption);
  }
  getInactivePatient():Observable<any>{
    return this.http.get<DoctorInactive>(this.urlPatient,headerOption);
  }
}
