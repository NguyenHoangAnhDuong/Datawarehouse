package com.example.datawarehousDBStaging.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckLoadData {

    @Autowired
    private ConfigService configService;

    @Autowired
    private FileConfigService fileConfigService;

    @Autowired
    private FileLogService fileLogService;

    @Autowired
    private EmailService emailService;

    @Autowired
    @Qualifier("controllerJdbcTemplate")
    private JdbcTemplate controllerJdbcTemplate;

    @Autowired
    @Qualifier("stagingJdbcTemplate")
    private JdbcTemplate stagingJdbcTemplate;

    public void loadDataToStaging() {

        // Lấy lên các file đã load (trạng thái là failed)
        List<String> failedFiles = fileLogService.checkFileLoadStatus();

        // Tất cả các file đều đã load thaành công,  gửi mail báo về hệ thống và kết thúc chương trình.
        if (failedFiles == null) {
            emailService.sendEmail(configService.getActiveConfig().getData().getErrorToEmail()
                    );
        } else {
            // Lấy danh sách các file chưa tải thành công.
            String failedFilesMessage = String.join("\n", failedFiles);

            //Thực hieện

        }
    }

    private void loadFileDataToStaging(String nameFile) {
        // Code để load dữ liệu từ file vào StagingDB
        // Cần tùy thuộc vào cấu trúc dữ liệu và quy trình xử lý file cụ thể
    }
}
