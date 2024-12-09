package com.example.scrapeDatafromWeb.service;

import com.example.scrapeDatafromWeb.entity.FileConfigEntity;
import com.example.scrapeDatafromWeb.repository.FileConfigRepository;
import com.example.scrapeDatafromWeb.request.FileLogRequest;
import com.example.scrapeDatafromWeb.utils.SSLUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ScrapeDataDOJI {

    @Autowired
    private FileLogService fileLogService;

    @Autowired
    private FileConfigRepository fileConfigRepository;

    private FileConfigEntity formatName(){
        FileConfigEntity entity = fileConfigRepository.findByNameAndEventAndStatus("gold_prices_doji_", "scrape", "active");

        return entity;
    }

    public void scrapeGoldPrices() {
        String url = "https://doji.vn/bang-gia-vang/";
//        String csvDirectory = "../scrapeCSV/";
        String xlsxDirectory = "../changeCSVtoXLSX/";

        try {
            // Fetch the webpage
            SSLUtils.disableSSLCertificateChecking();
            Document document = Jsoup.connect(url).timeout(5000).get();

            // Prepare date and file names
            String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//            String csvFilePath = csvDirectory + "gold_prices_doji_" + dateNow + ".csv";
            String csvFilePath = formatName().getPath() + dateNow + ".csv";
            String xlsxFilePath = xlsxDirectory + "gold_prices_doji_" + dateNow + ".xlsx";

            // Extract data
            List<GoldPriceRecordDOJI> records = new ArrayList<>();
            int idCounter = 1;

            Elements tables = document.select("div._table");
            for (Element table : tables) {
                String nameBranch = table.selectFirst("div._title-tax").text()
                        .replace("giá vàng trang sức tại", "")
                        .replace("giá vàng", "").trim();

                Elements goldTypes = table.select("div._taxonomy ._block");
                Elements buyPrices = table.select("div._buy ._block");
                Elements sellPrices = table.select("div._Sell ._block");

                for (int i = 0; i < goldTypes.size(); i++) {
                    String type = goldTypes.get(i).text().trim();
                    String buy = i < buyPrices.size() ? buyPrices.get(i).text().trim() + ",000" : "N/A";
                    String sell = i < sellPrices.size() ? sellPrices.get(i).text().trim() + ",000" : "N/A";

                    records.add(new GoldPriceRecordDOJI(idCounter++, type, nameBranch, buy, sell));
                }
            }

            // Save to CSV
            writeCsvFile(records, csvFilePath);

            // Convert to XLSX
            writeXlsxFile(records, xlsxFilePath);

            System.out.println("Dữ liệu đã được lưu thành công!");
            System.out.println("CSV: " + csvFilePath);
            FileLogRequest request = new FileLogRequest(csvFilePath, "scraper", "success");
            fileLogService.saveFileLogs(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCsvFile(List<GoldPriceRecordDOJI> records, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Id,TypeName,Company,BuyValue,SellValue\n");
            for (GoldPriceRecordDOJI record : records) {
                writer.append(record.toCsvLine()).append("\n");
            }
        }
    }

    private static void writeXlsxFile(List<GoldPriceRecordDOJI> records, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Gold Prices");

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Id", "TypeName", "Company", "BuyValue", "SellValue"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Data rows
        int rowIndex = 1;
        for (GoldPriceRecordDOJI record : records) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(record.getId());
            row.createCell(1).setCellValue(record.getTypeName());
            row.createCell(2).setCellValue(record.getCompany());
            row.createCell(3).setCellValue(record.getBuyValue());
            row.createCell(4).setCellValue(record.getSellValue());
        }

        // Write to file
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        workbook.write(Files.newOutputStream(path));
        workbook.close();
    }
}

class GoldPriceRecordDOJI {
    private final int id;
    private final String typeName;
    private final String company;
    private final String buyValue;
    private final String sellValue;

    public GoldPriceRecordDOJI(int id, String typeName, String company, String buyValue, String sellValue) {
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
