package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Documentation;
import com.skilldistillery.loantracker.repositories.DocumentationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DocumentationServicesImpl implements DocumentationService {

	@Autowired
    private DocumentationRepository docRepo;

    @Override
    public List<Documentation> findAll() {
        return docRepo.findAll();
    }

    @Override
    public Documentation findById(int id) {
        return docRepo.findById(id).orElse(null);
    }

    @Override
    public Documentation create(Documentation documentation) {
        return docRepo.save(documentation);
    }

    @Override
    public Documentation update(int id, Documentation documentation) {
        Optional<Documentation> opt = docRepo.findById(id);
        if (opt.isPresent()) {
            Documentation existing = opt.get();
            existing.setApplication(documentation.getApplication());
            existing.setDocType(documentation.getDocType());
            existing.setFilePath(documentation.getFilePath());
            existing.setUploadedAt(documentation.getUploadedAt());
            existing.setUploadedBy(documentation.getUploadedBy());
            return docRepo.save(existing);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (docRepo.existsById(id)) {
            docRepo.deleteById(id);
            return true;
        }
        return false;
    }
}