package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.FileConfig;

@Repository
public interface FileConfigRepository extends JpaRepository<FileConfig, Integer> {
}