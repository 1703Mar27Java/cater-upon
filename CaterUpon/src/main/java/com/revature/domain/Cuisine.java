package com.revature.domain;

import javax.persistence.*;

import com.revature.enums.Cuisines;

@Entity
@Table(name = "CU_CUISINES")
public class Cuisine {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuisineSeq")
	@SequenceGenerator(allocationSize = 1, name = "cuisineSeq", sequenceName = "CUISINE_SEQ")
	@Column(name = "CUISINE_ID")
	protected int caterer_Id;
	
	@Column(name = "CUISINE_TYPE")
	protected Cuisines cuisine_Type;

	public Cuisine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuisine(int caterer_Id, Cuisines cuisine_Type) {
		super();
		this.caterer_Id = caterer_Id;
		this.cuisine_Type = cuisine_Type;
	}

	public int getCaterer_Id() {
		return caterer_Id;
	}

	public void setCaterer_Id(int caterer_Id) {
		this.caterer_Id = caterer_Id;
	}

	public Cuisines getCuisine_Type() {
		return cuisine_Type;
	}

	public void setCuisine_Type(Cuisines cuisine_Type) {
		this.cuisine_Type = cuisine_Type;
	}
	
}

