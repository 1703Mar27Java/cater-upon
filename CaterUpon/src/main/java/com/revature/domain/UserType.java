package com.revature.domain;

import javax.persistence.*;

@Entity
@Table(name = "CU_USERTYPES")
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userTypeSeq")
	@SequenceGenerator(allocationSize = 1, name = "userTypeSeq", sequenceName = "USERTYPES_SEQ")
	@Column(name = "USERTYPE_ID")
	protected int userType_Id;
	
	@Column(name = "USERTYPE_TYPE")
	protected UserTypes userType_Type;
	
}
