package com.example.datawarehousDBStaging.entity;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "configs")
public class Config {
  private Integer id;
  private String stgSrcUsername;
  private String stgSrcPwd;
  private Integer stgSrcPort;
  private String stgDbName;
  private String stgSrcHost;

  private String whSrcUsername;
  private String whSrcPwd;
  private Integer whSrcPort;
  private String whDbName;
  private String whSrcHost;

  private String martSrcUsername;
  private String martSrcPwd;
  private Integer marthSrcPort;
  private String martDbName;
  private String martSrcHost;

  private String downloadPath;
  private String errorToMail;
  private String flag;
  private String status;

}
