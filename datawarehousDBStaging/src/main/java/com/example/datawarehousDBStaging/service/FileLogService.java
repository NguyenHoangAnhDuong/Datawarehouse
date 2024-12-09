package com.example.datawarehousDBStaging.service;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.entity.FileConfig;
import com.example.datawarehousDBStaging.entity.FileLog;
import com.example.datawarehousDBStaging.repository.ApiResponse;
import com.example.datawarehousDBStaging.repository.FileLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileLogService {
    @Autowired
    private FileLogRepository fileLogRepository;

    @Autowired
    private FileConfigService fileConfigService;


    private List<String> formatPathName() {

        List<String> pathNameList = new ArrayList<>();

        List<FileConfig> fileConfigs = fileConfigService.getActiveFileConfigs() ;

        String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        for(FileConfig fileConfig : fileConfigs){
            String path = fileConfig.getPath() + dateNow + ".csv";
            pathNameList.add(path);
        }

        return pathNameList;
    }

    public List<String> checkFileLoadStatus() {
        List<FileLog> fileLogList = fileLogRepository.findAll();
        List<String> pathNameList = formatPathName();

        List<String> successfulFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();

        for (String pathName : pathNameList) {
            boolean isPathNameValid = fileLogList.stream()
                    .anyMatch(fileLog -> fileLog.getName().equals(pathName) &&
                            fileLog.getEventType().equals("load-to-staging") &&
                            fileLog.getStatus().equals("completed"));

            if (isPathNameValid) {
                successfulFiles.add(pathName);
            } else {
                failedFiles.add(pathName);
            }
        }

        // Trả về danh sách các file chưa thành công và thành công
        return failedFiles.isEmpty() ? null : failedFiles; // Nếu không có file nào bị lỗi thì trả null
    }



//    public boolean isFileLoadedSuccessfully() {
//
//        List<FileLog> fileLogList = fileLogRepository.findAll();
//        // In ra fileLogList để kiểm tra
//        System.out.println("fileLogList: ");
//        fileLogList.forEach(fileLog -> System.out.println(fileLog));
//
//
//        List<String> pathNameList = formatPathName();
//
//        for(String pathName : pathNameList){
//            System.out.println("Tên file nhé: "+  pathName);
//            boolean isPathNameValid = fileLogList.stream()
//                    .anyMatch(fileLog -> fileLog.getName().equals(pathName) &&
//                            fileLog.getEventType().equals("load-to-staging") &&
//                            fileLog.getStatus().equals("completed"));
//
//            // Nếu không tìm thấy một bản ghi fileLog hợp lệ cho pathName này, trả về false
//            if (!isPathNameValid) {
//                return false;
//            }
//
//        }
//
//        return true;
//
//
//
//    }
}
