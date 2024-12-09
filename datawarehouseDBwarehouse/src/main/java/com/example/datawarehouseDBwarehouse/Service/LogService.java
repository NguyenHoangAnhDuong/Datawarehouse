package com.example.datawarehouseDBwarehouse.Service;

import com.example.datawarehouseDBwarehouse.Model.ControlLog;
import com.example.datawarehouseDBwarehouse.Repository.ControlLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LogService {

    @Autowired
    private ControlLogRepository controlLogRepository;

    public void logEvent(String eventType, String description) {
        ControlLog log = new ControlLog();
        log.setEventType(eventType);
        log.setStatus(description); // Cần sử dụng đúng trường, giả định là `status`
        log.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        controlLogRepository.save(log);
    }
}
