package com.example.datawarehouseDBwarehouse.Service;

import com.example.datawarehouseDBwarehouse.Model.StagingData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    public boolean validateData(List<StagingData> data) {
        // Kiểm tra dữ liệu trong Staging
        if (data == null || data.isEmpty()) {
            return false; // Không có dữ liệu
        }

        // Kiểm tra các điều kiện dữ liệu khác như kiểu dữ liệu, giá trị hợp lệ...
        for (StagingData item : data) {
                if (item.getBuyValue() <= 0 || item.getCreatedAt()  == null) {
                return false; // Dữ liệu không hợp lệ
            }
        }

        return true; // Dữ liệu hợp lệ
    }
}

