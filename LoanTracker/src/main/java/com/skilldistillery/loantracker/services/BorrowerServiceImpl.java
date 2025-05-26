package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.loantracker.entities.Borrower;
import com.skilldistillery.loantracker.repositories.BorrowerRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService {


	@Autowired
    private BorrowerRepository borrowerRepo;

    @Override
    public List<Borrower> findAll() {
        return borrowerRepo.findAll();
    }

    @Override
    public Borrower findById(int id) {
        Optional<Borrower> borrowerOpt = borrowerRepo.findById(id);
        return borrowerOpt.orElse(null);
    }

    @Override
    public Borrower create(Borrower borrower) {
        return borrowerRepo.save(borrower);
    }

    @Override
    public Borrower update(int id, Borrower borrower) {
        Borrower existing = findById(id);
        if (existing == null) return null;

        existing.setFirstName(borrower.getFirstName());
        existing.setLastName(borrower.getLastName());
        existing.setEmail(borrower.getEmail());
        existing.setPhone(borrower.getPhone());

        return borrowerRepo.save(existing);
    }

    @Override
    public boolean delete(int id) {
        if (borrowerRepo.existsById(id)) {
            borrowerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Borrower> findByFirstNameContains(String name) {
        return borrowerRepo.findByFirstNameContainingIgnoreCase(name);
    }

    @Override
    public List<Borrower> findByPhoneContains(String phone) {
        return borrowerRepo.findByPhoneContaining(phone);
    }
}