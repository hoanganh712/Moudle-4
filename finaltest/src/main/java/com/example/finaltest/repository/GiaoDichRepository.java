package com.example.finaltest.repository;

import com.example.finaltest.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {
    List<GiaoDich> findByKhachHang_TenKhachHangContainingAndLoaiDichVuContaining(String tenKhachHang, String loaiDichVu);
}
