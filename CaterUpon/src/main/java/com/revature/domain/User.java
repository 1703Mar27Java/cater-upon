package com.revature.domain;

import javax.persistence.*;

@Entity
@Table(name = "CU_USERS", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_USERNAME" }))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	@SequenceGenerator(allocationSize = 1, name = "userSeq", sequenceName = "USER_SEQ")
	// User Login Variables
	// User_Id
	@Column(name = "USER_ID")
	protected int user_Id;

	// User_Username
	@Column(name = "USER_USERNAME")
	protected String user_Username;

	// User_Password
	@Column(name = "USER_PASSWORD")
	protected String user_Password;

	// User_Email
	@Column(name = "USER_EMAIL")
	protected String user_Email;

	// User Google Login Variable
	protected int google_Id;
	protected String google_Username;

	// Additional User Variable
	// User_UserType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USERTYPE_TYPE")
	protected UserType user_UserType;

	// User_BankAccount
	@Column(name = "USER_BANKBALANCE")
	protected int user_BankBalance;

	// Constructors
	public User() {
		super();
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Username() {
		return user_Username;
	}

	public void setUser_Username(String user_Username) {
		this.user_Username = user_Username;
	}

	public String getUser_Password() {
		return user_Password;
	}

	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}

	public String getUser_Email() {
		return user_Email;
	}

	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}

	public int getGoogle_Id() {
		return google_Id;
	}

	public void setGoogle_Id(int google_Id) {
		this.google_Id = google_Id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + google_Id;
		result = prime * result + ((google_Username == null) ? 0 : google_Username.hashCode());
		result = prime * result + user_BankBalance;
		result = prime * result + ((user_Email == null) ? 0 : user_Email.hashCode());
		result = prime * result + user_Id;
		result = prime * result + ((user_Password == null) ? 0 : user_Password.hashCode());
		result = prime * result + ((user_UserType == null) ? 0 : user_UserType.hashCode());
		result = prime * result + ((user_Username == null) ? 0 : user_Username.hashCode());
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
		User other = (User) obj;
		if (google_Id != other.google_Id)
			return false;
		if (google_Username == null) {
			if (other.google_Username != null)
				return false;
		} else if (!google_Username.equals(other.google_Username))
			return false;
		if (user_BankBalance != other.user_BankBalance)
			return false;
		if (user_Email == null) {
			if (other.user_Email != null)
				return false;
		} else if (!user_Email.equals(other.user_Email))
			return false;
		if (user_Id != other.user_Id)
			return false;
		if (user_Password == null) {
			if (other.user_Password != null)
				return false;
		} else if (!user_Password.equals(other.user_Password))
			return false;
		if (user_UserType == null) {
			if (other.user_UserType != null)
				return false;
		} else if (!user_UserType.equals(other.user_UserType))
			return false;
		if (user_Username == null) {
			if (other.user_Username != null)
				return false;
		} else if (!user_Username.equals(other.user_Username))
			return false;
		return true;
	}

	public String getGoogle_Username() {
		return google_Username;
	}

	public void setGoogle_Username(String google_Username) {
		this.google_Username = google_Username;
	}

	@Override
	public String toString() {
		return "User [user_Id=" + user_Id + ", user_Username=" + user_Username + ", user_Password=" + user_Password
				+ ", user_Email=" + user_Email + ", google_Id=" + google_Id + ", google_Username=" + google_Username
				+ ", user_UserType=" + user_UserType.toString() + ", user_BankBalance=" + user_BankBalance + "]";
	}

	public User(int user_Id, String user_Username, String user_Password, String user_Email, int google_Id,
			String google_Username, UserType user_UserType, int user_BankBalance) {
		super();
		this.user_Id = user_Id;
		this.user_Username = user_Username;
		this.user_Password = user_Password;
		this.user_Email = user_Email;
		this.google_Id = google_Id;
		this.google_Username = google_Username;
		this.user_UserType = user_UserType;
		this.user_BankBalance = user_BankBalance;
	}

	public UserType getUser_UserType() {
		return user_UserType;
	}

	public void setUser_UserType(UserType user_UserType) {
		this.user_UserType = user_UserType;
	}

	public int getUser_BankBalance() {
		return user_BankBalance;
	}

	public void setUser_BankBalance(int user_BankBalance) {
		this.user_BankBalance = user_BankBalance;
	}

}