package com.bstack.bstackapp.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 72)
	private String teamName;
	
	@Column(length = 255)
	private String teamDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_account_id")
	private UserAccount owner;

	@OneToMany
	private List<UserAccount> players;

	public Team() {}


	public String getTeamName() {
		return this.teamName; 
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDetails() {
		return this.teamDetails;
	}

	public void setTeamDetails(String teamDetails) {
		this.teamDetails = teamDetails;
	}

	public void setOwner(UserAccount userAccount) {
		this.owner = userAccount;
	}

	@Override
	public String toString() {
		return String.format("teamName: %s, teamDetails: %s, Owner: %s", teamName, teamDetails, owner);
	}
}
