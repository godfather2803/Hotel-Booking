/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class CartItemDTO implements Serializable{
    private int roomID;
    private Long oneDayPrice;
    private String roomType;
    private Long price;
    private String checkInDate;
    private String checkOutDate;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    
    
    public Long getOneDayPrice() {
        return oneDayPrice;
    }

    public void setOneDayPrice(Long oneDayPrice) {
        this.oneDayPrice = oneDayPrice;
    }

    public CartItemDTO(int roomID, Long oneDayPrice, Long price, String checkInDate, String checkOutDate) {
        this.roomID = roomID;
        this.oneDayPrice = oneDayPrice;
        this.price = price;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    
    
    public CartItemDTO(int roomID, Long price, String checkInDate, String checkOutDate) {
        this.roomID = roomID;
        this.price = price;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public CartItemDTO() {
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    
}
