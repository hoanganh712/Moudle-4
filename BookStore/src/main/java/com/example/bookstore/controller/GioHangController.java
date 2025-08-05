package com.example.bookstore.controller;

import com.example.bookstore.model.Sach;
import com.example.bookstore.service.GioHangService;
import com.example.bookstore.service.SachService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GioHangController {

    @Autowired
    private SachService sachService;

    @Autowired
    private GioHangService gioHangService;

    @GetMapping("/gio-hang")
    public String hienThiGioHang(Model model, HttpSession session) {
        model.addAttribute("gioHang", gioHangService.getGioHangFromSession(session));
        return "gio-hang";
    }

    @PostMapping("/them-vao-gio/{id}")
    public String themVaoGio(@PathVariable Long id, @RequestParam(defaultValue = "1") int soLuong, HttpSession session) {
        Sach sach = sachService.getSachById(id);
        if (sach == null) {
            return "redirect:/";
        }
        gioHangService.themVaoGioHang(sach, soLuong, session);
        return "redirect:/gio-hang";
    }

    @GetMapping("/thanh-toan")
    public String thanhToan(HttpSession session) {
        gioHangService.thanhToan(session);
        return "thanh-toan-thanh-cong";
    }
}