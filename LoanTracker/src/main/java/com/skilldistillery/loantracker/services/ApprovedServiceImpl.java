package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Approved;
import com.skilldistillery.loantracker.repositories.ApprovedRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApprovedServiceImpl implements ApprovedService {

	@Autowired
    private ApprovedRepository approvedRepo;
	
	@Override
	public Optional<Approved> findById(int id) {
		Optional<Approved> opt = approvedRepo.findById(id);
        return opt.isPresent() ? Optional.of(opt.get()) : null;
	}

	@Override
	public List<Approved> findAll() {
		 return approvedRepo.findAll();
	}

	@Override
	public Optional<Approved> findByApplicationId(int appId) {
		  return approvedRepo.findByApplicationId(appId);
	}

	@Override
	public Approved create(Approved approved) {
		   return approvedRepo.save(approved);
	}

	@Override
	 public Approved update(int id, Approved approved) {
        return approvedRepo.findById(id)
                .map(existing -> {
                    existing.setApprovalDate(approved.getApprovalDate());
                    existing.setApprovalNotes(approved.getApprovalNotes());
                    existing.setApplication(approved.getApplication());
                    return approvedRepo.save(existing);
                }).orElse(null);
    }

	@Override
    public boolean delete(int id) {
        if (approvedRepo.existsById(id)) {
            approvedRepo.deleteById(id);
            return true;
        }
        return false;
    }

	
}
