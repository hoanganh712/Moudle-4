package com.example.bookstore.repository;

import com.example.bookstore.model.ChiTietGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Long> {
}

