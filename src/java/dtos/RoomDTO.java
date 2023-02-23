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
public class RoomDTO implements Serializable{
    private int roomID;
    private Integer roomTypeID;
    private String roomType;
    private Long price;
    private String description;
    private Boolean status;

    public RoomDTO() {
    }

    public RoomDTO(int roomID, Integer roomTypeID, String roomType, Long price, String description, Boolean status) {
        this.roomID = roomID;
        this.roomTypeID = roomTypeID;
        this.roomType = roomType;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Integer getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(Integer roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoomDTO{" + "roomID=" + roomID + ", roomTypeID=" + roomTypeID + ", roomType=" + roomType + ", price=" + price + ", description=" + description + ", status=" + status + '}';
    }

    
    
    
}
