package com.skilldistillery.loantracker.repositories;


import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.loantracker.entities.Declined;


public interface DeclinedRepository extends JpaRepository<Declined, Integer> {

	  List<Declined> findByApplicationId(int appId);
	  List<Declined> findByReasonContainingIgnoreCase(String keyword);
	  List<Declined> findByDeclinedDate(LocalDate date);
	    
}
