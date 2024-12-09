package com.example.scrapeDatafromWeb.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@Service
public class ScrapeDataPNJ {

//    public static void main(String[] args) {
//        // Chỉ cần chạy main, hàm này sẽ tự động thực hiện các bước cần thiết
//        scrapeGoldPrices();
//    }

    public void scrapeGoldPrices() {
        // URL cần cào dữ liệu
        String url = "https://bieudogiavang.vn/gia-vang-pnj";

        // Thư mục lưu file CSV và XLSX
        String csvDirectory = "../scrapeCSV/";
        String xlsxDirectory = "../changeCSVtoXLSX/";

        try {
            // Kết nối tới URL và lấy nội dung HTML
            Document document = Jsoup.connect(url).timeout(5000).get();

            // Lấy ngày hiện tại để đặt tên file
            String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String csvFilePath = csvDirectory + "gold_prices_pnj_" + dateNow + ".csv";
            String xlsxFilePath = xlsxDirectory + "gold_prices_pnj_" + dateNow + ".xlsx";

            // Cào dữ liệu từ bảng trên trang web
            List<GoldPriceRecordPNJ> records = new ArrayList<>();
            Elements rows = document.select(".min-w-full tbody tr");
            int idCounter = 1;

            for (Element row : rows) {
                Elements columns = row.select("td");
                if (columns.size() < 5) continue; // Bỏ qua các dòng không đủ cột

                String rank = columns.get(0).text().trim(); // (Không sử dụng nhưng vẫn có thể lấy)
                String type = columns.get(1).selectFirst("span").text().trim();
                String company = columns.get(2).text().trim();
                String buyPrice = columns.get(3).selectFirst("span").text()
                        .trim().replace(".", "").replace("\n", "");
                String sellPrice = columns.get(4).selectFirst("span").text()
                        .trim().replace(".", "").replace("\n", "");

                // Lưu từng dòng dữ liệu
                records.add(new GoldPriceRecordPNJ(idCounter++, type, company, buyPrice, sellPrice));
            }

            // Ghi dữ liệu ra tệp CSV
            writeCsvFile(records, csvFilePath);

            // Ghi dữ liệu ra tệp XLSX
            writeXlsxFile(records, xlsxFilePath);

            System.out.println("Dữ liệu đã được lưu thành công!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCsvFile(List<GoldPriceRecordPNJ> records, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Viết tiêu đề
            writer.append("Id,TypeName,Company,BuyValue,SellValue\n");
            for (GoldPriceRecordPNJ record : records) {
                // Ghi từng dòng dữ liệu
                writer.append(record.toCsvLine()).append("\n");
            }
        }
    }

    private static void writeXlsxFile(List<GoldPriceRecordPNJ> records, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Gold Prices");

        // Tạo hàng tiêu đề
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Id", "TypeName", "Company", "BuyValue", "SellValue"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Ghi dữ liệu từng dòng vào tệp Excel
        int rowIndex = 1;
        for (GoldPriceRecordPNJ record : records) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(record.getId());
            row.createCell(1).setCellValue(record.getTypeName());
            row.createCell(2).setCellValue(record.getCompany());
            row.createCell(3).setCellValue(record.getBuyValue());
            row.createCell(4).setCellValue(record.getSellValue());
        }

        // Tạo thư mục và lưu tệp Excel
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        workbook.write(Files.newOutputStream(path));
        workbook.close();
    }
}

// Lớp lưu trữ thông tin từng dòng dữ liệu
class GoldPriceRecordPNJ {
    private final int id;
    private final String typeName;
    private final String company;
    private final String buyValue;
    private final String sellValue;

    public GoldPriceRecordPNJ(int id, String typeName, String company, String buyValue, String sellValue) {
        this.id = id;
        this.typeName = typeName;
        this.company = company;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getCompany() {
        return company;
    }

    public String getBuyValue() {
        return buyValue;
    }

    public String getSellValue() {
        return sellValue;
    }

    public String toCsvLine() {
        return id + "," + typeName + "," + company + "," + buyValue + "," + sellValue;
    }
}
