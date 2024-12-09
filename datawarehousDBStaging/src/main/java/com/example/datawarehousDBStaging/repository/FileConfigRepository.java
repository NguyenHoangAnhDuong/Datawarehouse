package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.FileConfig;

import java.util.List;

@Repository
public interface FileConfigRepository extends JpaRepository<FileConfig, Long> {

    // Lấy danh sách các fileconfig cần kiểm tra.
    List<FileConfig> findByConfigEntityIdAndStatus(Long idConfig, String status);
}