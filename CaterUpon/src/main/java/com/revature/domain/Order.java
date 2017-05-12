package com.revature.domain;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "CU_ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
	@SequenceGenerator(allocationSize = 1, name = "orderSeq", sequenceName = "ORDER_SEQ")

	// Order Variables
	// Order_Id
	@Column(name = "ORDER_ID")
	protected int order_Id;

	// Order_Customer
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_CUSTOMER")
	protected User order_Customer;

	// Order_Caterer
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_CATERER")
	protected Caterer order_Caterer;

	// Order_Status
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_STATUS")
	protected StatusType order_Status;

	// Order_Amount
	@Column(name = "ORDER_AMOUNT")
	protected int order_Amount;

	// Order_Date
	@Column(name = "ORDER_DATE")
	protected Date order_Date;

	// Order_NumOfAttendees
	@Column(name = "ORDER_NUMOFATTENDEES")
	protected int order_NumOfAttendees;

	// Order_Comments
	@Column(name = "ORDER_COMMENT")
	protected String order_Comment;

	// Order_State
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_STATE")
	protected State order_State;

	// Order_City
	@Column(name = "ORDER_CITY")
	protected String order_City;

	@Column(name = "ORDER_ZIPCODE")
	protected int order_Zipcode;

	// flag to indicate if a review has been submitted for this order
	@Column(name = "ORDER_REVIEW_FLAG")
	protected int order_revFlag;

	public int getOrder_revFlag() {
		return order_revFlag;
	}
	
	public void setOrder_revFlag(int order_revFlag) {
		this.order_revFlag = order_revFlag;
	}

	public Order() {
		super();
		this.order_revFlag = 0;
		// TODO Auto-generated constructor stub
	}

	public int getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(int order_Id) {
		this.order_Id = order_Id;
	}

	public StatusType getOrder_Status() {
		return order_Status;
	}

	public void setOrder_Status(StatusType order_Status) {
		this.order_Status = order_Status;
	}

	public int getOrder_Amount() {
		return order_Amount;
	}

	public void setOrder_Amount(int order_Amount) {
		this.order_Amount = order_Amount;
	}

	public Date getOrder_Date() {
		return order_Date;
	}

	public void setOrder_Date(Date order_Date) {
		this.order_Date = order_Date;
	}

	public int getOrder_NumOfAttendees() {
		return order_NumOfAttendees;
	}

	public void setOrder_NumOfAttendees(int order_NumOfAttendees) {
		this.order_NumOfAttendees = order_NumOfAttendees;
	}

	public String getOrder_Comment() {
		return order_Comment;
	}

	public void setOrder_Comment(String order_Comment) {
		this.order_Comment = order_Comment;
	}

	public State getOrder_State() {
		return order_State;
	}

	public void setOrder_State(State order_State) {
		this.order_State = order_State;
	}

	public String getOrder_City() {
		return order_City;
	}

	public void setOrder_City(String order_City) {
		this.order_City = order_City;
	}

	public int getOrder_Zipcode() {
		return order_Zipcode;
	}

	public void setOrder_Zipcode(int order_Zipcode) {
		this.order_Zipcode = order_Zipcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order_Amount;
		result = prime * result + ((order_Caterer == null) ? 0 : order_Caterer.hashCode());
		result = prime * result + ((order_City == null) ? 0 : order_City.hashCode());
		result = prime * result + ((order_Comment == null) ? 0 : order_Comment.hashCode());
		result = prime * result + ((order_Customer == null) ? 0 : order_Customer.hashCode());
		result = prime * result + ((order_Date == null) ? 0 : order_Date.hashCode());
		result = prime * result + order_Id;
		result = prime * result + order_NumOfAttendees;
		result = prime * result + ((order_State == null) ? 0 : order_State.hashCode());
		result = prime * result + ((order_Status == null) ? 0 : order_Status.hashCode());
		result = prime * result + order_Zipcode;
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
		Order other = (Order) obj;
		if (order_Amount != other.order_Amount)
			return false;
		if (order_Caterer == null) {
			if (other.order_Caterer != null)
				return false;
		} else if (!order_Caterer.equals(other.order_Caterer))
			return false;
		if (order_City == null) {
			if (other.order_City != null)
				return false;
		} else if (!order_City.equals(other.order_City))
			return false;
		if (order_Comment == null) {
			if (other.order_Comment != null)
				return false;
		} else if (!order_Comment.equals(other.order_Comment))
			return false;
		if (order_Customer == null) {
			if (other.order_Customer != null)
				return false;
		} else if (!order_Customer.equals(other.order_Customer))
			return false;
		if (order_Date == null) {
			if (other.order_Date != null)
				return false;
		} else if (!order_Date.equals(other.order_Date))
			return false;
		if (order_Id != other.order_Id)
			return false;
		if (order_NumOfAttendees != other.order_NumOfAttendees)
			return false;
		if (order_State == null) {
			if (other.order_State != null)
				return false;
		} else if (!order_State.equals(other.order_State))
			return false;
		if (order_Status == null) {
			if (other.order_Status != null)
				return false;
		} else if (!order_Status.equals(other.order_Status))
			return false;
		if (order_Zipcode != other.order_Zipcode)
			return false;
		return true;
	}

	public User getOrder_Customer() {
		return order_Customer;
	}

	public void setOrder_Customer(User order_Customer) {
		this.order_Customer = order_Customer;
	}

	public Caterer getOrder_Caterer() {
		return order_Caterer;
	}

	public void setOrder_Caterer(Caterer order_Caterer) {
		this.order_Caterer = order_Caterer;
	}

	public Order(int order_Id, User order_Customer, Caterer order_Caterer, StatusType order_Status, int order_Amount,
			Date order_Date, int order_NumOfAttendees, String order_Comment, State order_State, String order_City,
			int order_Zipcode) {
		super();
		this.order_Id = order_Id;
		this.order_Customer = order_Customer;
		this.order_Caterer = order_Caterer;
		this.order_Status = order_Status;
		this.order_Amount = order_Amount;
		this.order_Date = order_Date;
		this.order_NumOfAttendees = order_NumOfAttendees;
		this.order_Comment = order_Comment;
		this.order_State = order_State;
		this.order_City = order_City;
		this.order_Zipcode = order_Zipcode;
	}

	@Override
	public String toString() {
		return "Order [order_Id=" + order_Id + ", order_Customer=" + order_Customer + ", order_Caterer=" + order_Caterer
				+ ", order_Status=" + order_Status + ", order_Amount=" + order_Amount + ", order_Date=" + order_Date
				+ ", order_NumOfAttendees=" + order_NumOfAttendees + ", order_Comment=" + order_Comment
				+ ", order_State=" + order_State + ", order_City=" + order_City + ", order_Zipcode=" + order_Zipcode
				+ "]";
	}

}