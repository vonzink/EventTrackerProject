package com.skilldistillery.loantracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.loantracker.entities.Underwriting;

public interface UnderwritingRepository extends JpaRepository<Underwriting, Integer> {

		Optional<Underwriting> findByApplicationId(int appId);
		List<Underwriting> findByDecisionIgnoreCase(String decision);
		List<Underwriting> findByFindingsContainingIgnoreCase(String findings);
		Optional<Underwriting> findByUnderwriterNameIgnoreCase(String name);
}
