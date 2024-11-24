package com.example.scrapeDatafromWeb;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScrapeDataSJC {

    public static void main(String[] args) {
        String apiUrl = "https://sjc.com.vn/GoldPrice/Services/PriceService.ashx";
        String csvDirectory = "../scrapeCSV/";
        String xlsxDirectory = "../changeCSVtoXLSX/";

        try {
            // Gọi API để lấy dữ liệu JSON
            String jsonData = fetchApiData(apiUrl);
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray data = jsonObject.getJSONArray("data");

            // Xử lý lưu trữ
            String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String csvFilePath = csvDirectory + "gold_prices_sjc_" + dateNow + ".csv";
            String xlsxFilePath = xlsxDirectory + "gold_prices_sjc_" + dateNow + ".xlsx";

            saveToCSV(data, csvFilePath);
            saveToExcel(data, xlsxFilePath);

            System.out.println("Dữ liệu đã được lưu thành công!");
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
