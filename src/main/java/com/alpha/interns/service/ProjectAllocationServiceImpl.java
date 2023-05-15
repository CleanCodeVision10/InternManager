package com.alpha.interns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alpha.interns.entity.Project;
import com.alpha.interns.dto.MentorDTO;
import com.alpha.interns.dto.ProjectDTO;
import com.alpha.interns.entity.Mentor;
import com.alpha.interns.exception.AlphaInternException;
import com.alpha.interns.repository.MentorRepository;
import com.alpha.interns.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	MentorRepository mentorRepository;

	@Autowired
	Environment environment;

	@Override
	public Project allocateProject(ProjectDTO project) throws AlphaInternException {
		Project project1 = new Project();
		project1.setProjectName(project.getProjectName());
		project1.setIdeaOwner(project.getIdeaOwner());
		project1.setReleaseDate(project.getReleaseDate());

		Optional<Mentor> mentor1 = mentorRepository.findById(project.getMentorDTO().getMentorName());

		if(mentor1.isEmpty()){
			throw new AlphaInternException(environment.getProperty("Service.MENTOR_NOT_FOUND"));
		}

		if(mentor1.get().getNumberOfProjectsMentored() >= 3){
			throw new AlphaInternException(environment.getProperty("Service.CANNOT_ALLOCATE_PROJECT"));

		}

		Mentor mentor = mentor1.get();
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		project1.setMentor(mentor);
		projectRepository.save(project1);
		return project1;
	}

	public List<Project> getMentorProject(String name) throws AlphaInternException {

		Optional<Mentor> mentor = mentorRepository.findById(name);
		if(mentor.isEmpty()) throw new AlphaInternException(environment.getProperty("Service.NO_DETAILS_FOUND"));

		List<Project> projects = projectRepository.findAllMentorProject(name);
		if (projects.size()==0) throw new AlphaInternException(environment.getProperty("Service.PROJECT_NOT_FOUND"));

		return projectRepository.findAllMentorProject(name);
	}


	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws AlphaInternException {

		List<Mentor> list = mentorRepository.getAllMentored(numberOfProjectsMentored);

		if(list.size() == 0) throw new AlphaInternException(environment.getProperty("Service.NO_MENTOR_FOUND"));
		List<MentorDTO> mentorDTOS = new ArrayList<>();
		list.forEach(p->{
			mentorDTOS.add(entityToDto(p));
		});
		return mentorDTOS;
	}


	@Override
	public void updateProjectMentor(Integer projectId, String mentorName) throws AlphaInternException {



		Optional<Project> project = projectRepository.findById(projectId);
		if (project.isEmpty()) throw new AlphaInternException(environment.getProperty("Service.PROJECT_NOT_FOUND"));

		Mentor mentor = mentorRepository.findById(mentorName).get();

		if(mentor.getNumberOfProjectsMentored()>=3) throw new AlphaInternException(environment.getProperty("Service.CANNOT_ALLOCATE_PROJECT"));
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		Mentor mentor1 = project.get().getMentor();
		project.get().setMentor(mentor);
		mentor1.setNumberOfProjectsMentored(mentor1.getNumberOfProjectsMentored()-1);


	}

	@Override
	public void deleteProject(Integer projectId) throws AlphaInternException {

		Optional<Project> project = projectRepository.findById(projectId);

		if (project.isEmpty()) throw new AlphaInternException(environment.getProperty("Service.PROJECT_NOT_FOUND"));

		Mentor mentor = project.get().getMentor();
		if(mentor.getNumberOfProjectsMentored()>0){
			mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()-1);
		}
		System.err.println("here");
		projectRepository.deleteById(projectId);

	}

	public MentorDTO entityToDto(Mentor mentor){
		MentorDTO mentorDTO = new MentorDTO();
		mentorDTO.setMentorName(mentor.getMentorName());
		mentorDTO.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
		return mentorDTO;
	}


}