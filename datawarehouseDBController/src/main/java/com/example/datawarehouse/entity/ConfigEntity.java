package com.example.datawarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "config")
public class ConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Number id;
    @Column(name = "status")
    private Number status;
    @Column(name = "staging_src_username")
    private String stagingSrcUsername;
    @Column(name = "staging_src_pwd")
    private String stagingSrcPwd;
    @Column(name = "staging_src_port")
    private String stagingSrcPort;
    @Column(name = "staging_db_name")
    private String stagingDbName;
    @Column(name = "staging_src_host")
    private String stagingSrcHost;
    @Column(name = "wh_src_username")
    private String whSrcUsername;
    @Column(name = "wh_src_pwd")
    private String whSrcPwd;
    @Column(name = "wh_src_port")
    private String whSrcPort;
    @Column(name = "wh_db_name")
    private String whDbName;
    @Column(name = "wh_src_host")
    private String whSrcHost;
    @Column(name = "mart_src_username")
    private String martSrcUsername;
    @Column(name = "mart_src_pwd")
    private String martSrcPwd;
    @Column(name = "mart_src_port")
    private String martSrcPort;
    @Column(name = "mart_db_name")
    private String martDbName;
    @Column(name = "mart_src_host")
    private String martSrcHost;
    @Column(name = "download_path")
    private String downloadPath;
    @Column(name = "error_to_mail")
    private String errorToMail;
    @Column(name = "flag")
    private String flag;



}
