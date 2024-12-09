package com.example.datawarehouseDBwarehouse.Service;

import com.example.datawarehouseDBwarehouse.Model.DateDim;
import com.example.datawarehouseDBwarehouse.Model.SourceDim;
import com.example.datawarehouseDBwarehouse.Model.StagingData;
import com.example.datawarehouseDBwarehouse.Model.FactGoldPrices;
import com.example.datawarehouseDBwarehouse.Repository.StagingDataRepository;
import com.example.datawarehouseDBwarehouse.Repository.FactGoldPricesRepository;
import com.example.datawarehouseDBwarehouse.Repository.DateDimRepository;
import com.example.datawarehouseDBwarehouse.Repository.SourceDimRepository;
import com.example.datawarehouseDBwarehouse.Util.DataNormalizer;
import com.example.datawarehouseDBwarehouse.Util.DuplicateRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ETLService {

    @Autowired
    private StagingDataRepository stagingDataRepository;

    @Autowired
    private FactGoldPricesRepository factGoldPricesRepository;

    @Autowired
    private DateDimRepository dateDimRepository;

    @Autowired
    private SourceDimRepository sourceDimRepository;

    @Autowired
    private DataNormalizer dataNormalizer;

    @Autowired
    private DuplicateRemover duplicateRemover;

    @Autowired
    private LogService logService;

    @Transactional
    public void executeETLProcess() {
        try {
            logService.logEvent("START", "ETL process started.");

            // Extract: Trích xuất dữ liệu từ Staging
            List<StagingData> stagingData = stagingDataRepository.findAll();
            if (stagingData.isEmpty()) {
                logService.logEvent("ERROR", "No Data in Staging.");
                throw new RuntimeException("No data found in Staging.");
            }

            // Transform: Làm sạch, chuẩn hóa, và loại bỏ bản ghi trùng lặp
            for (StagingData item : stagingData) {
                // Chuyển đổi ngày sử dụng phương thức chuẩn hóa
                String normalizedDate = dataNormalizer.normalizeDate(item.getCreatedAt().toString());
                item.setCreatedAt(LocalDateTime.parse(normalizedDate)); // Cập nhật ngày chuẩn hóa
            }

            stagingData = duplicateRemover.removeDuplicates(stagingData);

            // Load: Tải dữ liệu vào Data Warehouse
            List<FactGoldPrices> factGoldPricesList = transformToFactGoldPrices(stagingData);
            factGoldPricesRepository.saveAll(factGoldPricesList);

            logService.logEvent("SUCCESS", "ETL process completed successfully.");
        } catch (Exception e) {
            logService.logEvent("ERROR", "ETL process failed: " + e.getMessage());
            throw e;
        } finally {
            // Ghi log kết thúc
            logService.logEvent("END", "ETL process ended.");
        }
    }

    private List<FactGoldPrices> transformToFactGoldPrices(List<StagingData> stagingDataList) {
        return stagingDataList.stream().map(stagingData -> {
            FactGoldPrices factGoldPrices = new FactGoldPrices();

            // Ánh xạ các thuộc tính từ StagingData sang FactGoldPrices
            factGoldPrices.setBuyValue(stagingData.getBuyValue());
            factGoldPrices.setSellValue(stagingData.getSellValue());
            factGoldPrices.setTypeName(stagingData.getTypeName());
            factGoldPrices.setRegion(stagingData.getRegion());

            // Ánh xạ DateDim và SourceDim nếu cần
            DateDim dateDim = dateDimRepository.findByFullDate(LocalDate.from(stagingData.getCreatedAt()));  // Giả sử bạn có một phương thức tìm DateDim từ createdAt
            SourceDim sourceDim = sourceDimRepository.findBySource(stagingData.getSource());  // Tìm SourceDim theo nguồn

            factGoldPrices.setDate(dateDim);
            factGoldPrices.setSource(sourceDim);

            return factGoldPrices;
        }).collect(Collectors.toList());
    }

}
