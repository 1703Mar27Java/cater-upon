package com.revature.domain;

import javax.persistence.*;

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
	
}

