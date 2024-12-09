package com.example.datawarehouseDBwarehouse.Repository;

import com.example.datawarehouseDBwarehouse.Model.StagingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagingDataRepository extends JpaRepository<StagingData, Long> {
    // Tùy chọn: Bạn có thể thêm các phương thức truy vấn tùy chỉnh nếu cần
}
