package com.example.bookstore.service;

import com.example.bookstore.model.ChiTietGioHang;
import com.example.bookstore.model.GioHang;
import com.example.bookstore.model.Sach;
import com.example.bookstore.repository.ChiTietGioHangRepository;
import com.example.bookstore.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Service
public class GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private ChiTietGioHangRepository chiTietGioHangRepository;

    public GioHang getGioHangFromSession(HttpSession session) {
        return (GioHang) session.getAttribute("gioHang");
    }

    public void themVaoGioHang(Sach sach, int soLuong, HttpSession session) {
        GioHang gioHang = getGioHangFromSession(session);
        if (gioHang == null) {
            gioHang = new GioHang();
            session.setAttribute("gioHang", gioHang);
        }

        Optional<ChiTietGioHang> existingItem = gioHang.getChiTietGioHang().stream()
                .filter(item -> item.getSach().getId().equals(sach.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            ChiTietGioHang chiTiet = existingItem.get();
            chiTiet.setSoLuong(chiTiet.getSoLuong() + soLuong);
        } else {
            ChiTietGioHang chiTiet = new ChiTietGioHang();
            chiTiet.setSach(sach);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setGioHang(gioHang);
            gioHang.getChiTietGioHang().add(chiTiet);
        }
    }

    public void thanhToan(HttpSession session) {
        GioHang gioHang = getGioHangFromSession(session);
        if (gioHang != null) {
            gioHangRepository.save(gioHang);
            for (ChiTietGioHang chiTiet : gioHang.getChiTietGioHang()) {
                chiTietGioHangRepository.save(chiTiet);
            }
        }
        session.removeAttribute("gioHang");
    }
}
