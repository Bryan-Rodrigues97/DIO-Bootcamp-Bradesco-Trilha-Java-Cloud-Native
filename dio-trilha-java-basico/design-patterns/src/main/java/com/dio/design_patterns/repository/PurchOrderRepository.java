package com.dio.design_patterns.repository;

import com.dio.design_patterns.entity.PurchOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchOrderRepository extends JpaRepository<PurchOrder, Long> {
}
