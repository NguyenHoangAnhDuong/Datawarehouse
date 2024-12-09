
package com.example.datawarehousDBStaging.entity;


import jakarta.persistence.*;
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
  private Integer id;

  @Column(name = "name_file")
  private String nameFile;

  @Column(name = "event_type")
  private String eventType;

  @Column(name = "status")
  private String status;

  @Column(name = "create_on")
  private String createOn;
}
