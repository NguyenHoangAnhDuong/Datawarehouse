package com.example.datawarehouseDBwarehouse.controller;

import com.example.datawarehouseDBwarehouse.service.DataWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataWarehouseController {

    @Autowired
    private DataWarehouseService dataWarehouseService;

    @PostMapping("/loadData")
    public String startDataLoad() {
        try {
            dataWarehouseService.loadData();
            return "Data loading completed successfully!";
        } catch (Exception e) {
            return "Data loading failed: " + e.getMessage();
        }
    }
}
