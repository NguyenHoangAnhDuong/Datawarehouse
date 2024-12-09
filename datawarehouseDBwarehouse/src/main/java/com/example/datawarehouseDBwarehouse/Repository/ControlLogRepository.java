package com.example.datawarehouseDBwarehouse.Repository;

import com.example.datawarehouseDBwarehouse.Model.ControlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlLogRepository extends JpaRepository<ControlLog, Long> {
    // Tùy chọn: Các phương thức truy vấn tùy chỉnh nếu cần
}
