package com.revature.domain;

import javax.persistence.*;
@Embeddable
@Entity
@Table(name = "CU_CATERER")
public class Caterer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catererSeq")
	@SequenceGenerator(allocationSize = 1, name = "catererSeq", sequenceName = "CATERER_SEQ")
	
	@Column(name = "CATERER_ID")
	protected int caterer_Id;
	
	@Column(name = "CATERER_SEARCHRADIUS")
	protected int caterer_SearchRadius;
	
	@Column(name = "CATERER_DESCRIPTION")
	protected String caterer_Description;
	
	@ManyToOne
	@JoinColumn(name = "CATERER_STATE")
	protected State caterer_State;
	
	@Column(name = "CATERER_CITY")
	protected String caterer_City;
	
	@Column(name = "CATERER_ZIPCODE")
	protected int caterer_Zipcode;
		
	@Column(name = "CATERER_USER")
	protected int caterer_User;
	
	@Column(name = "CATERER_CuisineId")
	protected int caterer_CuisineId;

	public Caterer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Caterer(int caterer_Id, int caterer_SearchRadius, String caterer_Description, State caterer_State,
			String caterer_City, int caterer_Zipcode, int caterer_User) {
		super();
		this.caterer_Id = caterer_Id;
		this.caterer_SearchRadius = caterer_SearchRadius;
		this.caterer_Description = caterer_Description;
		this.caterer_State = caterer_State;
		this.caterer_City = caterer_City;
		this.caterer_Zipcode = caterer_Zipcode;
		this.caterer_User = caterer_User;
	}

	public int getCaterer_Id() {
		return caterer_Id;
	}

	public void setCaterer_Id(int caterer_Id) {
		this.caterer_Id = caterer_Id;
	}

	public int getCaterer_SearchRadius() {
		return caterer_SearchRadius;
	}

	public void setCaterer_SearchRadius(int caterer_SearchRadius) {
		this.caterer_SearchRadius = caterer_SearchRadius;
	}

	public int getCaterer_CuisineId() {
		return caterer_CuisineId;
	}

	public void setCaterer_CuisineId(int caterer_CuisineId) {
		this.caterer_CuisineId = caterer_CuisineId;
	}

	public String getCaterer_Description() {
		return caterer_Description;
	}

	public void setCaterer_Description(String caterer_Description) {
		this.caterer_Description = caterer_Description;
	}

	public State getCaterer_State() {
		return caterer_State;
	}

	public void setCaterer_State(State caterer_State) {
		this.caterer_State = caterer_State;
	}

	public String getCaterer_City() {
		return caterer_City;
	}

	public void setCaterer_City(String caterer_City) {
		this.caterer_City = caterer_City;
	}

	public int getCaterer_Zipcode() {
		return caterer_Zipcode;
	}

	public void setCaterer_Zipcode(int caterer_Zipcode) {
		this.caterer_Zipcode = caterer_Zipcode;
	}

	public int getCaterer_User() {
		return caterer_User;
	}

	public void setCaterer_User(int caterer_User) {
		this.caterer_User = caterer_User;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caterer_City == null) ? 0 : caterer_City.hashCode());
		result = prime * result + ((caterer_Description == null) ? 0 : caterer_Description.hashCode());
		result = prime * result + caterer_Id;
		result = prime * result + caterer_SearchRadius;
		result = prime * result + ((caterer_State == null) ? 0 : caterer_State.hashCode());
		result = prime * result + caterer_User;
		result = prime * result + caterer_Zipcode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caterer other = (Caterer) obj;
		if (caterer_City == null) {
			if (other.caterer_City != null)
				return false;
		} else if (!caterer_City.equals(other.caterer_City))
			return false;
		if (caterer_Description == null) {
			if (other.caterer_Description != null)
				return false;
		} else if (!caterer_Description.equals(other.caterer_Description))
			return false;
		if (caterer_Id != other.caterer_Id)
			return false;
		if (caterer_SearchRadius != other.caterer_SearchRadius)
			return false;
		if (caterer_State == null) {
			if (other.caterer_State != null)
				return false;
		} else if (!caterer_State.equals(other.caterer_State))
			return false;
		if (caterer_User != other.caterer_User)
			return false;
		if (caterer_Zipcode != other.caterer_Zipcode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return caterer_Id + "," + caterer_SearchRadius + "," + caterer_Description + "," + caterer_State
				+ "," + caterer_City + "," + caterer_Zipcode + ","	+ caterer_User + "," + caterer_CuisineId;
	}

	
}