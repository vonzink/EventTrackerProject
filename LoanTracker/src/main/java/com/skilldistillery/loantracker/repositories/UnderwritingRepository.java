package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.loantracker.entities.Underwriting;

public interface UnderwritingRepository extends JpaRepository<Underwriting, Integer> {

}
