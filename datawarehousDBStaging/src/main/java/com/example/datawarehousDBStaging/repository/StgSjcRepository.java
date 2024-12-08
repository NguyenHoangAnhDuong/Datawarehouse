package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.StgSjc;

@Repository
public interface StgSjcRepository extends JpaRepository<StgSjc, Integer> {
}