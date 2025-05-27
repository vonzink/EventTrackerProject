package com.skilldistillery.loantracker.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.loantracker.entities.Funded;
import com.skilldistillery.loantracker.repositories.FundedRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class FundedServiceImpl implements FundedService {

    @Autowired
    private FundedRepository fundedRepo;

    @Override
    public List<Funded> findAll() {
        return fundedRepo.findAll();
    }

    @Override
    public Funded findById(int id) {
        return fundedRepo.findById(id).orElse(null);
    }

    @Override
    public Funded findByApplicationId(int appId) {
        return fundedRepo.findByApplication_Id(appId).orElse(null);
    }

    @Override
    public Funded create(Funded funded) {
        return fundedRepo.save(funded);
    }


}
