package com.example.datawarehouseDBwarehouse.service;

import com.example.datawarehouseDBwarehouse.dao.DataWarehouseDAO;
import com.example.datawarehouseDBwarehouse.model.FactGoldPrice;
import com.example.datawarehouseDBwarehouse.model.DateDimension;
import com.example.datawarehouseDBwarehouse.model.SourceDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataWarehouseService {

    @Autowired
    private DataWarehouseDAO dataWarehouseDAO;

    public void loadData() {
        // Bước 1: Lấy dữ liệu từ DBStaging
        List<FactGoldPrice> rawData = dataWarehouseDAO.getDataFromStaging();

        // Bước 2: Làm sạch và xác thực dữ liệu
        List<FactGoldPrice> cleanedData = cleanAndValidateData(rawData);

        // Bước 3: Chuyển dữ liệu vào các bảng dimension và fact
        dataWarehouseDAO.insertIntoDimensionTables(cleanedData);
        dataWarehouseDAO.insertIntoFactTable(cleanedData);
    }

    private List<FactGoldPrice> cleanAndValidateData(List<FactGoldPrice> rawData) {
        // Logic làm sạch và xác thực dữ liệu
        // Ví dụ: loại bỏ null, kiểm tra giá trị âm, hoặc các quy tắc cụ thể
        return rawData.stream()
                .filter(data -> data.getPrice() > 0)
                .toList();
    }
}
