package com.example.booklocker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "students")
public class Students {
    @Id
    @Column(name = "student_id")
    private Integer studentId;
    private String studentName;
    private Integer studentContactNum;
    private String lockerNum;

    // 생성자, 게터/세터, 기타 메서드 생략

    // studentId의 equals() 및 hashCode() 오버라이딩
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return Objects.equals(studentId, students.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

//    public void setIsReserved(int i) {
//        if (i == 1) {
//            this.isReserved = true;
//        } else {
//            this.isReserved = false;
//        }
//    }

}

