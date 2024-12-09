
package com.example.datawarehousDBStaging.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file_config")
@Getter
@Setter
@NoArgsConstructor
public class FileConfig {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "id_config")
  private Integer config;

  @Column(name = "name_file")
  private String nameFile;

  @Column(name = "file_timestamp")
  private String fileTimestamp;

  @Column(name = "file_path")
  private String filePath;

  @Column(name = "file_collumn_list")
  private String fileColumnList;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "deleted")
  private Integer deleted;

  @Column(name = "status")
  private String status;

  @Column(name = "staging_table")
  private String stagingTable;
}