import { Injectable } from '@angular/core';
import { CanActivate } from "@angular/router";
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router'
//import { Observable } from "rxjs/Observable";
//import { Observable } from "rxjs/Observable"
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class GuardService implements CanActivate {
  
  constructor(private router: Router) { }  

  canActivate(route, state) {
    if (this.isLoggedIn()) {      
      return true;      
    }      
      // navigate to login page as user is not authenticated      
   this.router.navigate(['/login']);      
  return false;      
}      
public isLoggedIn(): boolean {      
   let status = false;  

   //if current user exists
   if (sessionStorage.getItem('currentUser')) {          
      status = true;      
   }    
   else {      
      status = false;      
      }      
   return status;      
   }    
}