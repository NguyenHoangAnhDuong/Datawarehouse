package com.example.datawarehouseDBwarehouse.Util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Cập nhật lại DataNormalizer:
@Component
public class DataNormalizer {

    // Chuẩn hóa ngày tháng theo định dạng yyyy-MM-dd
    public String normalizeDate(String dateStr) {  // bỏ static
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dateStr);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Chuẩn hóa số liệu (Loại bỏ dấu phân cách thập phân)
    public Double normalizeNumber(String numberStr) {  // bỏ static
        try {
            return Double.parseDouble(numberStr.replace(",", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}



