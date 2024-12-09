package com.example.datawarehousDBStaging.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "configs")
public class Config {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "staging_db_name")
  private String stagingDbName;

  @Column(name = "staging_source_host")
  private String stagingSourceHost;

  @Column(name = "staging_source_port")
  private int stagingSourcePort;

  @Column(name = "staging_source_username")
  private String stagingSourceUsername;

  @Column(name = "staging_source_password")
  private String stagingSourcePassword;

  @Column(name = "wh_db_name")
  private String whDbName;

  @Column(name = "wh_source_host")
  private String whSourceHost;

  @Column(name = "wh_source_port")
  private int whSourcePort;

  @Column(name = "wh_source_username")
  private String whSourceUsername;

  @Column(name = "wh_source_password")
  private String whSourcePassword;

  @Column(name = "mart_db_name")
  private String martDbName;

  @Column(name = "mart_source_host")
  private String martSourceHost;

  @Column(name = "mart_source_port")
  private int martSourcePort;

  @Column(name = "mart_source_username")
  private String martSourceUsername;

  @Column(name = "mart_source_password")
  private String martSourcePassword;

  @Column(name = "download_path")
  private String downloadPath;

  @Column(name = "error_to_email")
  private String errorToEmail;

  @Column(name = "flag")
  private String flag;

  @Column(name = "status")
  private String status;

  @OneToMany
  @JoinColumn(name = "config_id")
  private List<FileConfig> fileConfigEntities;

}
