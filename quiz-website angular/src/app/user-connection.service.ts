import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class UserConnectionService {

  constructor(private _http: HttpClient) { }

  public loginUserHTTP(user: User):Observable<any>
  {
    return this._http.post<boolean>("http://localhost:8080/login",user)
  }

  public registerUserHTTP(user: User):Observable<any>
  {
    return this._http.post<boolean>("http://localhost:8080/register",user)
  }
}
