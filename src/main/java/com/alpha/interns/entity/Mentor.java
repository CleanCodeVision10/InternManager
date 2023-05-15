package com.alpha.interns.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mentor {


	@Id
	private String mentorName;

	private Integer numberOfProjectsMentored;



	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public Integer getNumberOfProjectsMentored() {
		return numberOfProjectsMentored;
	}

	public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

	@Override
	public String toString() {
		return "Mentor{" +
				"mentorName='" + mentorName + '\'' +
				", numberOfProjectsMentored=" + numberOfProjectsMentored;
	}
}