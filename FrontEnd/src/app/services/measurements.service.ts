import { Injectable } from '@angular/core';
import {HttpClient,HttpParams,HttpHeaders} from '@angular/common/http';
import { Measurements } from '../classes/measurements';
import { Observable } from 'rxjs';

const headerOption = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' , 'Authorization': 'Basic ' + btoa(sessionStorage.getItem('credentials'))
                      })
      };

@Injectable({
  providedIn: 'root'
})
export class MeasurementsService {

  readonly url="http://localhost:9000/sacchon/measurements";

  addMeasurements: Measurements = {
    bloodGlucoseLevel: 0,
    carbIntake: 0,
    measurementDate: new Date,
    measurementID:0

  }
  constructor(private http: HttpClient) { }

  addDataMeasurements(data: Measurements): Observable<Measurements> {
    return this.http.post<Measurements>(this.url, data, headerOption);
  }
  getMeasurementsData(): Observable<Measurements[]> {
    return this.http.get<Measurements[]>(this.url,headerOption);
  }
  updateMediData(data: Measurements): Observable<Measurements>{
    return this.http.put<Measurements>(this.url,data,headerOption);
  }
  removeMedi(measurementID){
    return this.http.delete(this.url + "/" + measurementID, headerOption);
  }
}