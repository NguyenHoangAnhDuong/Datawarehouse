package com.example.scrapeDatafromWeb.repository;

import com.example.scrapeDatafromWeb.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {
    List<ConfigEntity> findAllByStatus(String status);
}
