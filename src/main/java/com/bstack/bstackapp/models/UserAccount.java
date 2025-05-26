package com.bstack.bstackapp.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable=false,length=72)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable=false)
	private String password;

	@Column(unique=true,nullable=false,length=128)
	private String email;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Team> teams;

	// default constructor
	public UserAccount() {}

	public UserAccount(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.teams = new ArrayList<>();
	}

	public void addTeam(Team team) {
		team.setOwner(this);
		teams.add(team);
	}

	public Long getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	} 

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("id=%d, username=%s, email=%s", id, username, email);
	}

}
