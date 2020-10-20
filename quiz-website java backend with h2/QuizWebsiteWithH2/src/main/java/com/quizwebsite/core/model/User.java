package com.quizwebsite.core.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name="Users")
public class User implements Persistable<String> {
	@Id
	@Column(columnDefinition="varchar(50)")
	private String username;
	@Column(columnDefinition="varchar(64)")
	private String pass;
	
	//this is to prevent save operation in jpa from updating. a flag to check if we can update
	@Transient
	private boolean update;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "test_id", referencedColumnName = "id")
    //private List<TestModel> tests;
    
    @OneToMany(mappedBy="user")
    private Set<TestModel> tests;
	
	public User(String pass, String username)
	{
		super();
		this.pass=pass;
		this.username=username;
		update = false;
	}
	
	public User()
	{
		super();
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	
	public String getPass()
	{
		return this.pass;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public void setPass(String pass)
	{
		this.pass=pass;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return username;
	}

	public boolean getUpdate() {
		return this.update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	@Override
	public boolean isNew() {
		return !this.update;
	}

	@PrePersist
	@PostLoad
	void markUpdated() {
		this.update = true;
	}
}