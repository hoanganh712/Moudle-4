package com.example.finaltest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
public class GiaoDich {
    @Id
    @NotBlank(message = "Mã giao dịch không được để trống")
    @Pattern(regexp = "^MGD-\\d{4}$", message = "Mã giao dịch phải có định dạng MGD-XXXX")
    private String maGiaoDich;

    @ManyToOne
    @JoinColumn(name = "maKhachHang")
    @NotNull(message = "Khách hàng không được để trống")
    private KhachHang khachHang;

    @NotBlank(message = "Loại dịch vụ không được để trống")
    private String loaiDichVu;

    @NotNull(message = "Ngày giao dịch không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayGiaoDich;

    @NotNull(message = "Đơn giá không được để trống")
    @DecimalMin(value = "500000", message = "Đơn giá phải lớn hơn 500.000 VND")
    private Double donGia;

    @NotNull(message = "Diện tích không được để trống")
    @DecimalMin(value = "20", message = "Diện tích phải lớn hơn 20 m2")
    private Double dienTich;
}
