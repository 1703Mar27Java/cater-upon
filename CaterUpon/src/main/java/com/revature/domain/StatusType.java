package com.revature.domain;

import javax.persistence.*;

import com.revature.enums.StatusTypes;

@Entity
@Table(name = "CU_STATUSTYPE")
public class StatusType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusSeq")
	@SequenceGenerator(allocationSize = 1, name = "statusSeq", sequenceName = "STATUS_SEQ")
	@Column(name = "STATUS_ID")
	protected int status_Id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_TYPE")
	protected StatusTypes status_Type;

	public StatusType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatusType(int status_Id, StatusTypes status_Type) {
		super();
		this.status_Id = status_Id;
		this.status_Type = status_Type;
	}

	public int getStatus_Id() {
		return status_Id;
	}

	public void setStatus_Id(int status_Id) {
		this.status_Id = status_Id;
	}

	public StatusTypes getStatus_Type() {
		return status_Type;
	}

	public void setStatus_Type(StatusTypes status_Type) {
		this.status_Type = status_Type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + status_Id;
		result = prime * result + ((status_Type == null) ? 0 : status_Type.hashCode());
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
		StatusType other = (StatusType) obj;
		if (status_Id != other.status_Id)
			return false;
		if (status_Type != other.status_Type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusType [status_Id=" + status_Id + ", status_Type=" + status_Type + "]";
	}
	
	
}

