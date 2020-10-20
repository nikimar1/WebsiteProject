package com.quizwebsite.core.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tests")
public class TestModel {
	
	 //@Id
	 //@GeneratedValue(strategy = GenerationType.AUTO)
	 //@Column(name = "testid")
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "test_id", referencedColumnName = "id")
	 //private Long id;
	 
	@Id
	@Column(columnDefinition="varchar(50)")
	private String testTitle;
	 
	@Column(name = "testCompleted")
	private boolean testCompleted;
	
	@OneToMany(mappedBy="testModel")
	private Set<QuestionModel> questions;
	
	@Column( name = "score")
	private String score;
	
	 
	//foreign key from user table mapped by username
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "id")
	//private User user;
	@ManyToOne
	@JoinColumn(name="User", nullable=false)
	private User user;
	 
	public TestModel()
	{
	 super();
	}
	 
	 
	public TestModel(String title, User user)
	{
		 this.testTitle= title;
		 this.user = user;
		 this.testCompleted=false;
	}
	 
	public void setCompleted(boolean completed)
	{
		this.testCompleted=completed;
	}
	
	public boolean getCompleted()
	{
		return this.testCompleted;
	}
	
	
	//for setting score upon test completion
	public void setScore(String score)
	{
		this.score=score;
	}

	public String getScore()
	{
		return this.score;
	}

	public String getTitle() {
		return this.testTitle;
	}
	
	public User getUser()
	{
		return user;
	}

}
