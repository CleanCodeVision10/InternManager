package com.alpha.interns;

import com.alpha.interns.dto.MentorDTO;
import com.alpha.interns.dto.ProjectDTO;
import com.alpha.interns.entity.Project;
import com.alpha.interns.exception.AlphaInternException;
import com.alpha.interns.repository.ProjectRepository;
import com.alpha.interns.service.ProjectAllocationService;
import com.alpha.interns.service.ProjectAllocationServiceImpl;
import com.alpha.interns.repository.MentorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
public class AlphaInternsApplicationTests {


	@Mock
	private Environment environment;
	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private MentorRepository mentorRepository;

	@InjectMocks
	private ProjectAllocationServiceImpl projectAllocationService = new ProjectAllocationServiceImpl();




	@Test
	public void allocateProjectMentorNotFoundTest() throws Exception {
		ProjectDTO project = new ProjectDTO();
		project.setProjectId(100);
		project.setProjectName("Spring");
		project.setIdeaOwner(10110);
		project.setReleaseDate(LocalDate.now());
		MentorDTO mentorDTO = new MentorDTO("Harris",0);
		project.setMentorDTO(mentorDTO);
		Mockito.when(environment.getProperty("Service.MENTOR_NOT_FOUND")).thenReturn("Mentor details not found");
		Mockito.when(mentorRepository.findById(project.getMentorDTO().getMentorName())).thenReturn(Optional.empty());
		AlphaInternException alphaInternException = Assertions.assertThrows(AlphaInternException.class,()->
			projectAllocationService.allocateProject(project)
		);
		Assertions.assertEquals("Mentor details not found",alphaInternException.getMessage());





	}
}