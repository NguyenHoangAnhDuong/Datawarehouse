package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.TempSjc;

@Repository
public interface TempSjcRepository extends JpaRepository<TempSjc, String> {
}