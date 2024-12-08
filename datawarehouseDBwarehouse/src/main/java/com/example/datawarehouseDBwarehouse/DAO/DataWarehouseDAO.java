package com.example.datawarehouseDBwarehouse.dao;

import com.example.datawarehouseDBwarehouse.model.FactGoldPrice;
import com.example.datawarehouseDBwarehouse.model.DateDimension;
import com.example.datawarehouseDBwarehouse.model.SourceDimension;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataWarehouseDAO {

    public List<FactGoldPrice> getDataFromStaging() {
        // Lấy dữ liệu từ bảng staging (vd: stg_sjc, stg_doj)
        // Thực hiện query database
        return List.of(); // Trả về danh sách dữ liệu
    }

    public void insertIntoFactTable(List<FactGoldPrice> factData) {
        // Ghi dữ liệu vào bảng fact_gold_prices
    }

    public void insertIntoDimensionTables(List<FactGoldPrice> factData) {
        // Tạo dữ liệu cho bảng dimension (date_dim, source_dim)
    }
}
