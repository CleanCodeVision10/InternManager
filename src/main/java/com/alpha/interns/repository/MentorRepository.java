package com.alpha.interns.repository;

import com.alpha.interns.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor,String>
{
    // add methods if required
    @Query("Select c from Mentor c where c.numberOfProjectsMentored= :number")
    List<Mentor> getAllMentored(@Param("number") Integer number);

}
