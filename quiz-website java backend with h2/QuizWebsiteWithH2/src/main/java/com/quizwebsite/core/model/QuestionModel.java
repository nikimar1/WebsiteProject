package com.quizwebsite.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Questions")
public class QuestionModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    
	@Column(name = "QuestionBody")
	private String questionBody;
	
	@Column(name = "QuestionNumber")
	private int questionNumber;
	 
	@Column(name = "choiceA")
	private String a;
	
	@Column(name = "choiceB")
	private String b;
	
	@Column(name = "choiceC")
	private String c;
	
	@Column( name = "choiceD")
	private String d;
	
	@Column(name = "answer",columnDefinition="varchar(1)")
	private String answer;
	
	@Column(name = "testTitle")
	private String testTitle;
	
	@JsonIgnore
	@Column(name = "correct")
	private boolean correct;
	
	//for resetting to false when test is not completed
	public void resetCorrect()
	{
		this.correct=false;
	}
	
	 
	//foreign key from user table mapped by username
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "id")
	//private User user;
	@ManyToOne
	@JoinColumn(name="testModel", nullable=false)
	private TestModel testModel;
	 
	public QuestionModel()
	{
	 super();
	}
	
	public QuestionModel( String testTitle, int questionNumber)
	{
		 super();
		 this.testTitle = testTitle;
		 this.questionNumber = questionNumber;
	}
	 
	 
	public QuestionModel(String questionBody, int questionNumber, TestModel test, String a, String b, String c, String d, String answer)
	{
		 this.questionBody= questionBody;
		 this.testModel = test;
		 this.testTitle= test.getTitle();
		 this.a=a;
		 this.b=b;
		 this.c=c;
		 this.d=d;
		 this.answer=answer;
		 this.questionNumber=questionNumber;
	}
	
	public boolean getCorrect()
	{
		return this.correct;
	}
	 
	public void setAnswer(String answer)
	{
		this.answer=answer;
	}
	
	public int getQuestionNumber()
	{
		return questionNumber;
	}
	
	public String getChoiceA()
	{
		return a;
	}
	
	public String getChoiceB()
	{
		return b;
	}

	public String getChoiceC()
	{
		return c;
	}
	
	public String getChoiceD()
	{
		return d;
	}

	public long getId()
	{
		return this.id;
	}


	public String getQuestionBody() {
		return this.questionBody;
	}
	
	public boolean checkAnswer(String answer)
	{
		if( answer.toLowerCase().equals(this.answer))
		{
			this.correct=true;
			return true;
		}
		return false;
	}
	
	public TestModel getTest()
	{
		return testModel;
	}
	
	public void setTest(TestModel test)
	{
		this.testModel= test;
	}

	public String getTestTitle()
	{
		return testTitle;
	}
	
	public void setTestTitle(String title)
	{
		this.testTitle = title;
	}
	
}