/*import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
*/

import { Component, OnInit } from '@angular/core';
import {TestService} from '../test.service';
import {QuestionService} from '../question.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})

export class TestComponent implements OnInit {

  //question: String
  //question: Question;

  public sessionStorage = sessionStorage;
  questions: Observable<any>;
  currentQuestionBody: String;
  currentChoiceA: String;
  currentChoiceB: String;
  currentChoiceC: String;
  currentChoiceD: String;
  currentQuestionId: number;
  currentScore: string

  totalQuestions:number;

  currentQuestionTestTitle = sessionStorage.getItem("currentTest");

  constructor(private questionService: QuestionService,  private _router: Router) { }

  currentQuestionNumber: number;

  ngOnInit(): void {
    this.currentQuestionNumber= 1;
    this.totalQuestions=0;
    this.initialPostLoadQuestions();
    this.loadCurrentQuestion(this.currentQuestionNumber)
  }

  initialPostLoadQuestions()
  {
    this.questions = this.questionService.getQuestion(this.currentQuestionTestTitle);
  }

  loadCurrentQuestion(curerentQuestion: number){

    this.questions.subscribe(
      data => 
      {
        for (var key in data) {
          this.totalQuestions++;
        //console.log("Key: " + key);
        //console.log("Value: " +data[key]);
        }


        //console.log(data);
        
        this.currentQuestionId= data[this.currentQuestionNumber-1].id;
        this.currentQuestionBody= data[this.currentQuestionNumber-1].questionBody;
        this.currentChoiceA= data[this.currentQuestionNumber-1].choiceA;
        this.currentChoiceB= data[this.currentQuestionNumber-1].choiceB;
        this.currentChoiceC= data[this.currentQuestionNumber-1].choiceC;
        this.currentChoiceD= data[this.currentQuestionNumber-1].choiceD;
      
      }
    )

    
    

  }

  onClick(answer: String): void {

    
    
    //console.log(question);
    //this.questionService.startTest(test);
    //this._router.navigate(['/test'])

    if((this.currentQuestionNumber)<=this.totalQuestions)
    {
      this.questionService.sendAnswer(answer, this.currentQuestionTestTitle, this.currentQuestionNumber).subscribe(
        data=>
        {
          console.log("Sent answer and data is")
          console.log(data)

          this.currentQuestionNumber++;
          console.log("incremented number of current question");
          
          //load next question if not out of bounds
          if((this.currentQuestionNumber+1)<this.totalQuestions)
          {
            this.loadCurrentQuestion(this.currentQuestionNumber);
          }
    
          else
          {
            console.log("submitting test now")
            console.log("test string is " + this.currentQuestionTestTitle)
            //do a post to make test completed and then redirect to list of tests
            //
            this.questionService.submitTest(this.currentQuestionTestTitle).subscribe
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
    
                   //this._router.navigate(['/score'])
                this.scoreNavigate()
      
               
              }
      
            )
         
      
          }


        }
      );

    
    } 

    
    //submit when done iterating over questions
   
    
    
  }

  scoreNavigate()
  {
    this._router.navigate(['/score'])
  }

}