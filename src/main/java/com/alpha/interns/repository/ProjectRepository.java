package com.alpha.interns.repository;

import com.alpha.interns.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Integer>
{

    // add methods if required
    @Query("Select p from Project p where p.mentor.mentorName= :name")
    List<Project> findAllMentorProject(@Param(value = "name") String name);

}
