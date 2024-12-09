package com.example.scrapeDatafromWeb.service;

import com.example.scrapeDatafromWeb.entity.FileConfigEntity;
import com.example.scrapeDatafromWeb.repository.FileConfigRepository;
import com.example.scrapeDatafromWeb.request.FileLogRequest;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Component
@Service
public class ScrapeDataSJC {
    @Autowired
    private FileLogService fileLogService;

    @Autowired
    private FileConfigRepository fileConfigRepository;

    private FileConfigEntity formatName(){
        FileConfigEntity entity = fileConfigRepository.findByNameAndEventAndStatus("gold_prices_sjc_", "scrape", "active");

        return entity;
    }

    public  void scrapeData() {
        String apiUrl = "https://sjc.com.vn/GoldPrice/Services/PriceService.ashx";
//        String csvDirectory = "../scrapeCSV/";
        String xlsxDirectory = "../changeCSVtoXLSX/";

        try {
            // Gọi API để lấy dữ liệu JSON
            String jsonData = fetchApiData(apiUrl);
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray data = jsonObject.getJSONArray("data");

            // Xử lý lưu trữ
            String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//            String csvFilePath = csvDirectory + "gold_prices_sjc_" + dateNow + ".csv";
            String csvFilePath = formatName().getPath() + dateNow + ".csv";
            String xlsxFilePath = xlsxDirectory + "gold_prices_sjc_" + dateNow + ".xlsx";

            saveToCSV(data, csvFilePath);
            saveToExcel(data, xlsxFilePath);

            System.out.println("Dữ liệu đã được lưu thành công!");
            System.out.println("CSV: " + csvFilePath);
            FileLogRequest request = new FileLogRequest(csvFilePath, "scraper", "success");
            fileLogService.saveFileLogs(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToCSV(JSONArray data, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Header
            String[] header = {"Id", "TypeName", "BranchName", "Buy", "Sell", "BuyValue", "SellValue"};
            writer.writeNext(header);

            // Data rows
            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                String[] row = {
                        String.valueOf(obj.getInt("Id")),
                        obj.getString("TypeName"),
                        obj.getString("BranchName"),
                        obj.getString("Buy"),
                        obj.getString("Sell"),
                        String.valueOf(obj.getDouble("BuyValue")),
                        String.valueOf(obj.getDouble("SellValue"))
                };
                writer.writeNext(row);
            }

        }
    }

    public static void saveToExcel(JSONArray data, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Gold Prices");

            // Header
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Id", "TypeName", "BranchName", "Buy", "Sell", "BuyValue", "SellValue"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Data rows
            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(obj.getInt("Id"));
                row.createCell(1).setCellValue(obj.getString("TypeName"));
                row.createCell(2).setCellValue(obj.getString("BranchName"));
                row.createCell(3).setCellValue(obj.getString("Buy"));
                row.createCell(4).setCellValue(obj.getString("Sell"));
                row.createCell(5).setCellValue(obj.getDouble("BuyValue"));
                row.createCell(6).setCellValue(obj.getDouble("SellValue"));
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            workbook.write(Files.newOutputStream(Paths.get(filePath)));
        }
    }

    public static String fetchApiData(String apiUrl) throws IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        connection.disconnect();
        return response.toString();
    }
}
