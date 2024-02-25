package com.example.bookmyticket.dto;

import java.util.List;

public class BookingRequest {
    private String userId;
    private String showId;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getShowId() {
        return showId;
    }
    public void setShowId(String showId) {
        this.showId = showId;
    }
    public List<String> getSeatIds() {
        return seatIds;
    }
    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }
    private List<String> seatIds;

    
}
