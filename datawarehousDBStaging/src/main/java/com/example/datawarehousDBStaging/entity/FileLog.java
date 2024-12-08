
package com.example.datawarehousDBStaging.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file_log")
@Getter
@Setter
@NoArgsConstructor
public class FileLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name_file")
  private String nameFile;

  @Column(name = "event_type")
  private String eventType;

  @Column(name = "status")
  private String status;

  @Column(name = "create_on")
  private String createOn;
}
