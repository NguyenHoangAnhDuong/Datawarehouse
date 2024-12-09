package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.FileLog;

@Repository
public interface FileLogRepository extends JpaRepository<FileLog, Integer> {
}