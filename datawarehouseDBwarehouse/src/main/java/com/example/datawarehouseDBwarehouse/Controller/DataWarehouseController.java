package com.example.datawarehouseDBwarehouse.Controller;

import com.example.datawarehouseDBwarehouse.Service.ETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/datawarehouse")
public class DataWarehouseController {

    @Autowired
    private ETLService etlService;

    // API để bắt đầu quy trình ETL
    @PostMapping("/start-etl")
    public ResponseEntity<String> startETL() {
        try {
            etlService.executeETLProcess();
            return ResponseEntity.ok("ETL process started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("ETL process failed: " + e.getMessage());
        }
    }

    // API kiểm tra trạng thái hệ thống
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        // Trả về trạng thái hệ thống (có thể kiểm tra kết nối DB hoặc trạng thái dịch vụ)
        return ResponseEntity.ok("System is running.");
    }
}
