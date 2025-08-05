package com.example.bookstore.service;

import com.example.bookstore.model.Sach;
import com.example.bookstore.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SachService {

    @Autowired
    private SachRepository sachRepository;

    public List<Sach> getAllSach() {
        return sachRepository.findAll();
    }

    public Sach getSachById(Long id) {
        return sachRepository.findById(id).orElse(null);
    }

    public Sach saveSach(Sach sach) {
        return sachRepository.save(sach);
    }

    public void deleteSach(Long id) {
        sachRepository.deleteById(id);
    }
}
