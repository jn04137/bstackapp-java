package com.bstack.bstackapp.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false, length = 72)
	private String teamName;
	
	@Column(length = 255)
	private String teamDetails;

	@ManyToOne
	private UserAccount owner;

	@OneToMany
	private List<UserAccount> players;

	public Team() {}


	public String getTeamName() {
		return this.teamName; 
	}

	public String getTeamDetails() {
		return this.teamDetails;
	}
}
