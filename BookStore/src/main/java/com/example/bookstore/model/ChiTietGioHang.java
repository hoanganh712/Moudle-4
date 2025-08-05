package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChiTietGioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sach_id")
    private Sach sach;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;

    private int soLuong;
}

