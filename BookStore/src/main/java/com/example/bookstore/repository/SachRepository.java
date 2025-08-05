package com.example.bookstore.repository;

import com.example.bookstore.model.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SachRepository extends JpaRepository<Sach, Long> {
}
