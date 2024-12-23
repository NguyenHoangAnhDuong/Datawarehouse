package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.TempDoji;

@Repository
public interface TempDojiRepository extends JpaRepository<TempDoji, String> {
}