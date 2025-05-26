package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.loantracker.entities.Underwriting;
import com.skilldistillery.loantracker.repositories.UnderwritingRepository;

@Service
public class UnderwritingServiceImpl implements UnderwritingService {

	 @Autowired
	    private UnderwritingRepository uwRepo;

	    @Override
	    public List<Underwriting> findAll() {
	        return uwRepo.findAll();
	    }

	    @Override
	    public Underwriting findById(int id) {
	        Optional<Underwriting> opt = uwRepo.findById(id);
	        return opt.isPresent() ? opt.get() : null;
	    }

	    @Override
	    public List<Underwriting> findByDecision(String decision) {
	        return uwRepo.findByFindingsContainingIgnoreCase(decision);
	    }

	    @Override
	    public List<Underwriting> findByFindings(String findings) {
	        return uwRepo.findByFindingsContainingIgnoreCase(findings);
	    }

	    @Override
	    public Underwriting create(Underwriting underwriting) {
	        return uwRepo.save(underwriting);
	    }

	    @Override
	    public Underwriting update(int id, Underwriting underwriting) {
	        Underwriting existing = findById(id);
	        if (existing != null) {
	            existing.setDecision(underwriting.getDecision());
	            existing.setFindings(underwriting.getFindings());
	            existing.setReviewedDate(underwriting.getReviewedDate());
	            existing.setUnderwriterName(underwriting.getUnderwriterName());
	            existing.setApplication(underwriting.getApplication());
	            return uwRepo.save(existing);
	        }
	        return null;
	    }

	    @Override
	    public boolean delete(int id) {
	        if (uwRepo.existsById(id)) {
	            uwRepo.deleteById(id);
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public Optional<Underwriting> findByUnderwriterName(String name) {
	    	 Optional<Underwriting> opt = uwRepo.findByUnderwriterNameIgnoreCase(name);
	    	 return opt.isPresent() ? Optional.of(opt.get()) : null;
	    }

		@Override
		public Optional<Underwriting> findByApplicationId(int appId) {
			 Optional<Underwriting> opt = uwRepo.findByApplicationId(appId);
			 return opt.isPresent() ? Optional.of(opt.get()) : null;
		}
}