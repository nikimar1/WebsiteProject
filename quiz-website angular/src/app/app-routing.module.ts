import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GuardService } from './guard.service';
import {LoginSuccessComponent } from './login-success/login-success.component';
import { LoginComponent } from './login/login.component';
import { RegisterSuccessComponent } from './register-success/register-success.component';
import { RegistrationComponent } from './registration/registration.component';
import { TestComponent } from './test/test.component';

const routes: Routes = [
  //{ path: '**', redirectTo: '', pathMatch: 'full' },
  {path:'',component:LoginComponent},
  {path:'loginsuccess',component:LoginSuccessComponent, canActivate : [GuardService]},
  {path:'register',component:RegistrationComponent},
  {path:'registersuccess',component:RegisterSuccessComponent},
  {path:'test',component:TestComponent,  canActivate : [GuardService]},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
