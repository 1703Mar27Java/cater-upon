package com.revature.domain;

import javax.persistence.*;

import com.revature.enums.UserTypes;

@Entity
@Table(name = "CU_USERTYPES")
public class UserType {
	//TODO
	/*
	 * org.hibernate.AnnotationException: @OneToOne or @ManyToOne on com.revature.domain.User.user_UserType references an unknown entity: int
	 * org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.AnnotationException: @OneToOne or @ManyToOne on com.revature.domain.User.user_UserType references an unknown entity: int
	 * 
	 */
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userTypeSeq")
	@SequenceGenerator(allocationSize = 1, name = "userTypeSeq", sequenceName = "USERTYPES_SEQ")
	@Column(name = "USERTYPE_ID")
	protected int userType_Id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "USERTYPE_TYPE")
	protected UserTypes userType_Type;

	public UserType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserType(int userType_Id, UserTypes userType_Type) {
		super();
		this.userType_Id = userType_Id;
		this.userType_Type = userType_Type;
	}

	public int getUserType_Id() {
		return userType_Id;
	}

	public void setUserType_Id(int userType_Id) {
		this.userType_Id = userType_Id;
	}

	public UserTypes getUserType_Type() {
		return userType_Type;
	}

	public void setUserType_Type(UserTypes userType_Type) {
		this.userType_Type = userType_Type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userType_Id;
		result = prime * result + ((userType_Type == null) ? 0 : userType_Type.hashCode());
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
		UserType other = (UserType) obj;
		if (userType_Id != other.userType_Id)
			return false;
		if (userType_Type != other.userType_Type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserType [userType_Id=" + userType_Id + ", userType_Type=" + userType_Type + "]";
	}

	
	
	
}
