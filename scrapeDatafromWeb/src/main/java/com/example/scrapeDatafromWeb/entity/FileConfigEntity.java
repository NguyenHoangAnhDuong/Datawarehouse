package com.example.scrapeDatafromWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_configs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_timestamp")
    private LocalDateTime fileTimestamp;

    @Column(name = "path")
    private String path;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "file_column_list")
    private String fileColumnList;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "status")
    private String status;

    @Column(name = "event")
    private String event;

    @ManyToOne
    @JoinColumn(name = "config_id")
    private ConfigEntity configEntity;



}
