package com.duan.demo01.repositories;

import com.duan.demo01.models.QR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRRepo extends JpaRepository<QR, Integer> {
}
