package com.example.scrapeDatafromWeb.repository;

import com.example.scrapeDatafromWeb.entity.FileLogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileLogRepository extends JpaRepository<FileLogsEntity, Long> {
}
