package com.example.booklocker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;
    private Integer studentId;
    private String studentName;
    private Integer studentContactNum;
    private String lockerNum;
    private String location;
    private String term;
}
