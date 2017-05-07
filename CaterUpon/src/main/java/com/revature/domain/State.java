package com.revature.domain;

import javax.persistence.*;

import com.revature.enums.States;

@Entity
@Table(name = "CU_STATES")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stateSeq")
	@SequenceGenerator(allocationSize = 1, name = "stateSeq", sequenceName = "STATE_SEQ")
	@Column(name = "STATE_ID")
	protected int state_Id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATE_NAME")
	protected States state_Name;

	public State(int state_Id, States state_Name) {
		super();
		this.state_Id = state_Id;
		this.state_Name = state_Name;
	}

	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getState_Id() {
		return state_Id;
	}

	public void setState_Id(int state_Id) {
		this.state_Id = state_Id;
	}

	public States getState_Name() {
		return state_Name;
	}

	public void setState_Name(States state_Name) {
		this.state_Name = state_Name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + state_Id;
		result = prime * result + ((state_Name == null) ? 0 : state_Name.hashCode());
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
		State other = (State) obj;
		if (state_Id != other.state_Id)
			return false;
		if (state_Name != other.state_Name)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [state_Id=" + state_Id + ", state_Name=" + state_Name + "]";
	}

	
}


