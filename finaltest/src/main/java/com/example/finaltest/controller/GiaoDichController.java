package com.example.finaltest.controller;

import com.example.finaltest.model.GiaoDich;
import com.example.finaltest.repository.GiaoDichRepository;
import com.example.finaltest.repository.KhachHangRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {

    @Autowired
    private GiaoDichRepository giaoDichRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @GetMapping
    public String hienThiDanhSach(Model model) {
        model.addAttribute("danhSachGiaoDich", giaoDichRepository.findAll());
        return "danh-sach";
    }

    @GetMapping("/them-moi")
    public String hienThiFormThem(Model model) {
        model.addAttribute("giaoDich", new GiaoDich());
        model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
        model.addAttribute("loaiDichVu", new String[]{"Nhà", "Đất", "Nhà và đất"});
        return "them-moi";
    }

    @PostMapping("/them-moi")
    public String themMoiGiaoDich(@Valid @ModelAttribute("giaoDich") GiaoDich giaoDich,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("danhSachKhachHang", khachHangRepository.findAll());
            model.addAttribute("loaiDichVu", new String[]{"Nhà", "Đất", "Nhà và đất"});
            return "them-moi";
        }
        giaoDichRepository.save(giaoDich);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới giao dịch thành công!");
        return "redirect:/giao-dich";
    }

    @GetMapping("/chi-tiet/{maGiaoDich}")
    public String hienThiChiTiet(@PathVariable String maGiaoDich, Model model) {
        Optional<GiaoDich> giaoDichOpt = giaoDichRepository.findById(maGiaoDich);
        if (giaoDichOpt.isPresent()) {
            model.addAttribute("giaoDich", giaoDichOpt.get());
            return "chi-tiet";
        }
        return "redirect:/giao-dich";
    }

    @PostMapping("/xoa/{maGiaoDich}")
    public String xoaGiaoDich(@PathVariable String maGiaoDich, RedirectAttributes redirectAttributes) {
        giaoDichRepository.deleteById(maGiaoDich);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa giao dịch thành công!");
        return "redirect:/giao-dich";
    }

    @GetMapping("/tim-kiem")
    public String timKiemGiaoDich(@RequestParam(name = "tenKhachHang", required = false) String tenKhachHang,
                                  @RequestParam(name = "loaiDichVu", required = false) String loaiDichVu,
                                  Model model) {
        List<GiaoDich> ketQuaTimKiem;
        if ((tenKhachHang != null && !tenKhachHang.isEmpty()) || (loaiDichVu != null && !loaiDichVu.isEmpty())) {
            ketQuaTimKiem = giaoDichRepository.findByKhachHang_TenKhachHangContainingAndLoaiDichVuContaining(tenKhachHang, loaiDichVu);
        } else {
            ketQuaTimKiem = giaoDichRepository.findAll();
        }
        model.addAttribute("danhSachGiaoDich", ketQuaTimKiem);
        model.addAttribute("tenKhachHang", tenKhachHang);
        model.addAttribute("loaiDichVu", loaiDichVu);
        return "danh-sach";
    }
}
