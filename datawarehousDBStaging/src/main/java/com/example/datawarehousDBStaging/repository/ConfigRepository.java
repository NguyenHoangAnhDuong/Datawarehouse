package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.Config;


@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

    // Kiểm tra status trên table config.
    Config findByStatus(String status);
}