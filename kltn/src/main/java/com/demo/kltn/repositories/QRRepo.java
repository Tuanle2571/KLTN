package com.demo.kltn.repositories;

import com.demo.kltn.models.QR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRRepo extends JpaRepository<QR, Integer> {
}
