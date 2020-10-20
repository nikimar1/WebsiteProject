import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {TestService} from '../test.service';
import { Router } from '@angular/router';
import {QuestionService} from '../question.service';

@Component({
  selector: 'app-login-success',
  templateUrl: './login-success.component.html',
  styleUrls: ['./login-success.component.css']
})
export class LoginSuccessComponent implements OnInit {

  public sessionStorage = sessionStorage;
  testsDone: Observable<any>;
  testsPending: Observable<any>;
  currentScore: string

  constructor(private testService: TestService, private questionService: QuestionService, private _router: Router) { }
 
  ngOnInit() {
    this.reloadData();

   

  }
 
  onClick(test: string): void {
    console.log(test);
    this.testService.startTest(test);
    this._router.navigate(['/test'])
  }

  onClickCompleted(test: string): void{

    console.log(test)

    sessionStorage.setItem("currentTest", test)

    console.log("submitting test now")
    console.log("test string is " + test)
    
    //some get method for getting correct score on completed tests. needs to be for completed tests only
    this.questionService.submitTest(test).subscribe
    (
      data =>
      {

        console.log("start of inside subscribe to submit test http return")
        //console.log("got score of:")
        //console.log(data)
        //inefficient to use session storage so often but how else can I pass values to another html page. global variables? 
        console.log(data);
        this.currentScore = data;  
        console.log( this.currentScore);
        sessionStorage.setItem("currentScore", this.currentScore)

        console.log(sessionStorage.getItem("currentScore"))

        console.log("end of inside subscribe to submit test http return")
        //console.log("got score of:")
        //console.log(data)
        //inefficient to use session storage so often but how else can I pass values to another html page. global variables? 
        //console.log(data);
        //this.currentScore = data;
        //sessionStorage.setItem("currentScore", this.currentScore)
        

        //trying to make this a method so that it is synchronous and only occurs after the subscription 
        this.scoreNavigate()
  
           
      }
  
    )

    
  
  }

  scoreNavigate()
  {
    this._router.navigate(['/score'])
  }
  reloadData() {
    this.testsDone = this.testService.getTestsByCompletion(true);
    this.testsPending = this.testService.getTestsByCompletion(false);

    /*this.testsDone.subscribe
      (
        data =>
        {
          console.log(data)
        }
      )
     */ 
  }
}
