package com.alpha.interns.dto;

public class MentorDTO {


	private String mentorName;
	private Integer numberOfProjectsMentored;

	public MentorDTO() {
		super();
	}

	public MentorDTO( String mentorName, Integer numberOfProjectsMentored) {
		super();

		this.mentorName = mentorName;
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}



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

}
