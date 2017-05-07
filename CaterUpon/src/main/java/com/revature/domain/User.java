package com.revature.domain;

import javax.persistence.*;

@Entity
@Table(name = "CU_USERS", uniqueConstraints=@UniqueConstraint(columnNames = {"USER_USERNAME"}))
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_USERTYPE")
	protected UserType user_UserType;

	// User_BankAccount
	@Column(name = "USER_BANKBALANCE")
	protected int user_BankBalance;

	// Constructors
	public User() {
		super();
	}

	public User(int user_Id, String user_Username, String user_Password, String user_Email, int google_Id,
			String google_Username, State user_State, String user_City, int user_SearchRadius, int user_ZipCode,
			UserType user_UserType) {
		this();
		this.user_Id = user_Id;
		this.user_Username = user_Username;
		this.user_Password = user_Password;
		this.user_Email = user_Email;
		this.google_Id = google_Id;
		this.google_Username = google_Username;
		this.user_UserType = user_UserType;
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

	public String getGoogle_Username() {
		return google_Username;
	}

	public void setGoogle_Username(String google_Username) {
		this.google_Username = google_Username;
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

	// ToString
	@Override
	public String toString() {
		return "User [user_Id=" + user_Id + ", user_Username=" + user_Username + ", user_Password=" + user_Password
				+ ", user_Email=" + user_Email + ", google_Id=" + google_Id + ", google_Username=" + google_Username
				+ ", user_UserType=" + user_UserType + ", user_BankBalance=" + user_BankBalance + "]";
	}

	
}