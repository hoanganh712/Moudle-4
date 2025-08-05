package com.example.bookstore.controller;

import com.example.bookstore.model.Sach;
import com.example.bookstore.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SachController {

    @Autowired
    private SachService sachService;

    @GetMapping("/")
    public String trangChu(Model model) {
        model.addAttribute("danhSachSach", sachService.getAllSach());
        return "index";
    }

    @GetMapping("/admin")
    public String quanLySach(Model model) {
        model.addAttribute("danhSachSach", sachService.getAllSach());
        model.addAttribute("sach", new Sach());
        return "admin";
    }

    @PostMapping("/admin/them")
    public String themSach(@ModelAttribute Sach sach) {
        sachService.saveSach(sach);
        return "redirect:/admin";
    }

    @GetMapping("/admin/sua/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        Sach sach = sachService.getSachById(id);
        if (sach == null) {
            return "error"; // Hoặc trang lỗi phù hợp
        }
        model.addAttribute("sach", sach);
        return "sua-sach";
    }

    @PostMapping("/admin/sua/{id}")
    public String suaSach(@PathVariable Long id, @ModelAttribute Sach sach) {
        sach.setId(id);
        sachService.saveSach(sach);
        return "redirect:/admin";
    }

    @GetMapping("/admin/xoa/{id}")
    public String xoaSach(@PathVariable Long id) {
        sachService.deleteSach(id);
        return "redirect:/admin";
    }
}
