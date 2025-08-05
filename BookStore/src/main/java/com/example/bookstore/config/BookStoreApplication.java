package com.example.bookstore.config;

import com.example.bookstore.model.Sach;
import com.example.bookstore.repository.SachRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(SachRepository sachRepository ) {
		return args -> {
			if (sachRepository.count() == 0) {
				sachRepository.save(createSach("Harry Potter và Hòn đá Phù thủy", "J.K. Rowling", 150000.0, "Cuốn sách đầu tiên trong loạt truyện.", "https://via.placeholder.com/200x300?text=Harry+Potter"));
				sachRepository.save(createSach("Đắc Nhân Tâm", "Dale Carnegie", 120000.0, "Cuốn sách kinh điển về nghệ thuật đối nhân xử thế.", "https://via.placeholder.com/200x300?text=Dac+Nhan+Tam"));
				sachRepository.save(createSach("Nhà Giả Kim", "Paulo Coelho", 100000.0, "Một cuốn tiểu thuyết đầy cảm hứng về hành trình theo đuổi ước mơ.", "https://via.placeholder.com/200x300?text=Nha+Gia+Kim"));
				sachRepository.save(createSach("Ông Già và Biển Cả", "Ernest Hemingway", 80000.0, "Một câu chuyện ngắn về cuộc chiến đấu của con người với tự nhiên.", "https://via.placeholder.com/200x300?text=Ong+Gia+Va+Bien+Ca"));
			}
		};
	}

	private Sach createSach(String ten, String tacGia, double gia, String moTa, String hinhAnh) {
		Sach sach = new Sach();
		sach.setTenSach(ten);
		sach.setTacGia(tacGia);
		sach.setGia(gia);
		sach.setMoTa(moTa);
		sach.setHinhAnh(hinhAnh);
		return sach;
	}
}