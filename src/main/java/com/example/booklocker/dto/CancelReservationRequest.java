package com.example.booklocker.dto;


// 예약 취소 요청 데이터를 전달하기 위한 DTO 클래스
public class CancelReservationRequest {

    private String lockerNum;

    public String getLockerNum() {
        return lockerNum;
    }

    public void setLockerNum(String lockerNum) {
        this.lockerNum = lockerNum;
    }
}
