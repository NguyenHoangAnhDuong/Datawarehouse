package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.FileLog;

import java.util.List;

@Repository
public interface FileLogRepository extends JpaRepository<FileLog, Long> {

    // Phương thức tìm kiếm FileLog theo name, eventType và status
    List<FileLog> findByNameAndEventTypeAndStatus(String pathName, String eventType, String status);


}