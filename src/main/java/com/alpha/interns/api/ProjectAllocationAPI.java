package com.alpha.interns.api;

import java.util.List;

import com.alpha.interns.dto.ProjectDTO;
import com.alpha.interns.entity.Project;
import com.alpha.interns.service.ProjectAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alpha.interns.dto.MentorDTO;
import com.alpha.interns.exception.AlphaInternException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class ProjectAllocationAPI
{
    @Autowired
    ProjectAllocationService projectAllocationService;

    @Autowired
    Environment environment;

    // add new project along with your approved mentor details
    @PostMapping(value = "/addProject")
    public ResponseEntity<String> allocateProject(@Valid @RequestBody ProjectDTO project) throws AlphaInternException
    {
       Project project1 = projectAllocationService.allocateProject(project);
        return new ResponseEntity<>(environment.getProperty("API.ALLOCATION_SUCCESS")+" " + project1.getProjectId(), HttpStatus.CREATED);
    }



    // get all mentors based on number of projects they are mentoring.
    @GetMapping(value = "getMentor/{id}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable(value = "id") Integer numberOfProjectsMentored) throws AlphaInternException
    {
        return new ResponseEntity<>(projectAllocationService.getMentors(numberOfProjectsMentored),HttpStatus.OK);
    }



    // delete a project using project ID
    @DeleteMapping(value = "/project/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable(value = "id") Integer projectId) throws AlphaInternException
    {
        projectAllocationService.deleteProject(projectId);
        return new ResponseEntity<>(environment.getProperty("API.PROJECT_DELETE_SUCCESS"),HttpStatus.CREATED);
    }


    // update the mentor of a project using project ID
    @PutMapping(value = "updateProjectMentor/{projectId}/{mentorName}")
    public ResponseEntity<String> updateProjectMentor(@PathVariable(value = "projectId") Integer projectId, @PathVariable(value = "mentorName")  String mentorName) throws AlphaInternException
    {
        projectAllocationService.updateProjectMentor(projectId,mentorName);
        return new ResponseEntity<>(environment.getProperty("API.PROJECT_UPDATE_SUCCESS"),HttpStatus.CREATED);
    }

    //  Get all projects that are mentored by a particular mentor
    @GetMapping(value = "/{name}")
    public ResponseEntity<List<Project>> getMentorProject(@PathVariable(value = "name") String name) throws AlphaInternException
    {
        return new ResponseEntity<>(projectAllocationService.getMentorProject(name),HttpStatus.OK);
    }


}
