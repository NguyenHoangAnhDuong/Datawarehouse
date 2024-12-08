package com.example.datawarehousDBStaging.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configs")
@Getter
@Setter
@NoArgsConstructor
public class Config {
  private Integer id;
  @Column(name = "staging_src_username")
  private String stgSrcUsername;
  @Column(name = "staging_src_pwd")
  private String stgSrcPwd;
  @Column(name = "staging_src_port")
  private Integer stgSrcPort;
  @Column(name = "staging_db_name")
  private String stgDbName;
  @Column(name = "staging_src_host")
  private String stgSrcHost;

  @Column(name = "wh_src_username")
  private String whSrcUsername;
  @Column(name = "wh_src_pwd")
  private String whSrcPwd;
  @Column(name = "wh_src_port")
  private Integer whSrcPort;
  @Column(name = "wh_db_name")
  private String whDbName;
  @Column(name = "wh_src_host")
  private String whSrcHost;

  @Column(name = "mart_src_username")
  private String martSrcUsername;
  @Column(name = "mart_src_pwd")
  private String martSrcPwd;
  @Column(name = "mart_src_port")
  private Integer marthSrcPort;
  @Column(name = "mart_db_name")
  private String martDbName;
  @Column(name = "mart_src_host")
  private String martSrcHost;

  @Column(name = "download_path")
  private String downloadPath;
  @Column(name = "error_to_mail")
  private String errorToMail;

  private String flag;
  private String status;

}
