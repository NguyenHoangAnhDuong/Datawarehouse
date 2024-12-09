package com.example.scrapeDatafromWeb.repository;

import com.example.scrapeDatafromWeb.entity.ConfigEntity;
import com.example.scrapeDatafromWeb.entity.FileConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileConfigRepository extends JpaRepository<FileConfigEntity, Long> {

    List<FileConfigEntity> findAllByStatusAndEvent(String status, String event);

    FileConfigEntity findByNameAndEventAndStatus(String name, String event, String status);
}
