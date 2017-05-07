package com.revature.domain;

import javax.persistence.*;

@Entity
@Table(name = "CU_REVIEW")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewSeq")
	@SequenceGenerator(allocationSize = 1, name = "reviewSeq", sequenceName = "REVIEW_SEQ")
	@Column(name = "REVIEW_ID")
	protected int review_Id;
	
	@Column(name = "REVIEW_RATING")
	protected int review_Rating;
	
	@Column(name = "REVIEW_CATERER")
	protected int review_Caterer;
	
	@Column(name = "REVIEW_AUTHOR")
	protected int review_Author;
	
	@Column(name = "REVIEW_MESSAGE")
	protected String review_Message;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int review_Id, int review_Rating, int review_Caterer, int review_Author, String review_Message) {
		super();
		this.review_Id = review_Id;
		this.review_Rating = review_Rating;
		this.review_Caterer = review_Caterer;
		this.review_Author = review_Author;
		this.review_Message = review_Message;
	}

	public int getReview_Id() {
		return review_Id;
	}

	public void setReview_Id(int review_Id) {
		this.review_Id = review_Id;
	}

	public int getReview_Rating() {
		return review_Rating;
	}

	public void setReview_Rating(int review_Rating) {
		this.review_Rating = review_Rating;
	}

	public int getReview_Caterer() {
		return review_Caterer;
	}

	public void setReview_Caterer(int review_Caterer) {
		this.review_Caterer = review_Caterer;
	}

	public int getReview_Author() {
		return review_Author;
	}

	public void setReview_Author(int review_Author) {
		this.review_Author = review_Author;
	}

	public String getReview_Message() {
		return review_Message;
	}

	public void setReview_Message(String review_Message) {
		this.review_Message = review_Message;
	}

	@Override
	public String toString() {
		return "Review [review_Id=" + review_Id + ", review_Rating=" + review_Rating + ", review_Caterer="
				+ review_Caterer + ", review_Author=" + review_Author + ", review_Message=" + review_Message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + review_Author;
		result = prime * result + review_Caterer;
		result = prime * result + review_Id;
		result = prime * result + ((review_Message == null) ? 0 : review_Message.hashCode());
		result = prime * result + review_Rating;
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
		Review other = (Review) obj;
		if (review_Author != other.review_Author)
			return false;
		if (review_Caterer != other.review_Caterer)
			return false;
		if (review_Id != other.review_Id)
			return false;
		if (review_Message == null) {
			if (other.review_Message != null)
				return false;
		} else if (!review_Message.equals(other.review_Message))
			return false;
		if (review_Rating != other.review_Rating)
			return false;
		return true;
	}
}
