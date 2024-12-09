package com.example.datawarehouseDBwarehouse.Repository;

import com.example.datawarehouseDBwarehouse.Model.FactGoldPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactGoldPricesRepository extends JpaRepository<FactGoldPrices, Long> {
    // Tùy chọn: Phương thức truy vấn tùy chỉnh nếu cần
}
