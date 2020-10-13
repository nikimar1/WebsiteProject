package com.quizwebsite.core.model;

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
	private String TestTitle;
	 
	@Column(name = "testCompleted")
	private boolean testCompleted;
	
	 
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
		 this.TestTitle= title;
		 this.user = user;
		 this.testCompleted=false;
	}
	 
	public void setCompleted(boolean completed)
	{
		this.testCompleted=completed;
	}


	public String getTitle() {
		return this.TestTitle;
	}
	
	public User getUser()
	{
		return user;
	}

}
