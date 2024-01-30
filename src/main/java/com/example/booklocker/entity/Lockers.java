package com.example.booklocker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Lockers {
    @Id
    private String lockerNum;
    private String location;
    private Integer status;

    public int getStatus() {
        return status;
    }


}
