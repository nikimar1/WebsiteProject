import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private _http: HttpClient) { }
  
  //question: String;
  //question = new Question()
 

  public getQuestion(testTitle: String): Observable<any>
  {


    
    //get username from token
    var parsed = JSON.parse(sessionStorage.getItem('currentUser'));


    //store parameters for post request
    //var parsedTest = JSON.parse(this.currentTest);
    //console.log(parsedTest)//.title)
    //console.log(this.currentTest)


    //question: Question = new Question()


    //new Question(this.currentTest,questionNumber);
    
    //console.log(new Question(this.currentTest,questionNumber))

    //make post request and return result
    return this._http.post<any>("http://localhost:8081/questions",testTitle/*JSON.stringify(new QuestionDeserializer(sessionStorage.getItem("currentTest"),questionNumber))*/, { 
      headers: { 
        //'Access-Control-Allow-Origin': '*',
        //"Authorization": "Bearer " + )
        "Authorization": parsed.username
      }} )
      /*.subscribe( data => 
        {
          console.log(data);
        }
        
        )*/

    //return this.question;
  }

  public sendAnswer(ans: String, testTitle: String, qnNumber: number )
  {

    var parsed = JSON.parse(sessionStorage.getItem('currentUser'));

    var testAndAnswer = { "answer" : ans, "testTitle" : testTitle, "qnNumber" : qnNumber}

    //console.log (parsed.username)

    return this._http.post<any>("http://localhost:8081/answer" ,testAndAnswer,
    { 
      headers: 
      { 
        "Authorization": parsed.username
      }
    })
  }

  public submitTest(testTitle: String): Observable<any>
  {
    var parsed = JSON.parse(sessionStorage.getItem('currentUser'));

    console.log("doing post for submit test")
    console.log(testTitle)

    return this._http.post<any>("http://localhost:8081/submission" , testTitle,  
    {  

     
      headers: 
      { 
        "Authorization": parsed.username
      }
    })


  }
}
