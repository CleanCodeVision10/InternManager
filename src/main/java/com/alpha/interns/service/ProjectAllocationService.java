package com.alpha.interns.service;

import java.util.List;

import com.alpha.interns.dto.MentorDTO;
import com.alpha.interns.dto.ProjectDTO;
import com.alpha.interns.entity.Project;
import com.alpha.interns.exception.AlphaInternException;


public interface ProjectAllocationService {

	public Project allocateProject(ProjectDTO projectAllocation) throws AlphaInternException;

	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws AlphaInternException;
	
	public void updateProjectMentor(Integer projectId, String mentorName) throws AlphaInternException;
	
	public void deleteProject(Integer projectId) throws AlphaInternException;

	public List<Project> getMentorProject(String name) throws AlphaInternException;
}
