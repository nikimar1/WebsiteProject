import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  currentTest : string;

  private baseUrl = 'http://localhost:8081/tests';

  //var currentUser = JSON.parse(`${sessionStorage.getItem("currentUser")}`;

  constructor(private _http: HttpClient) { }
  
  getTestsByCompletion(completed: boolean): Observable<any> {
    //later delete this console log. it's for debugging purposes
    //console.log(this.http.get(`${this.baseUrl}/completed/${completed}`))
    //var currentUser = sessionStorage.getItem('currentUser')

    var parsed = JSON.parse(sessionStorage.getItem('currentUser'));


    //console.log(parsed);

     //how do I pass current user to backend via post? In the header seems ok
    return this._http.get(`${this.baseUrl}/completed/${completed}`, { 
      headers: { 
        //'Access-Control-Allow-Origin': '*',
        //"Authorization": "Bearer " + )
        "Authorization": parsed.username
      }}
    )
  }


  startTest( testTitle: string)
  {

    this.currentTest= testTitle;
    sessionStorage.setItem("currentTest", this.currentTest)
    //return this._http.post<boolean>("http://localhost:8081/testing",testTitle)
  }


  
}
