package com.revature.domain;

import javax.persistence.*;

@Entity
@Table(name = "CU_STATUSTYPE")
public class StatusType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusSeq")
	@SequenceGenerator(allocationSize = 1, name = "statusSeq", sequenceName = "STATUS_SEQ")
	@Column(name = "STATUS_ID")
	protected int status_Id;
	
	@Column(name = "STATUS_TYPE")
	protected StatusTypes status_Type;
	
}

