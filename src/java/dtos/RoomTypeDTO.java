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
public class RoomTypeDTO implements Serializable{
    private int roomTypeID;
    private String roomType;
    private Long price;
    private String description;

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(int roomTypeID, String roomType, Long price, String description) {
        this.roomTypeID = roomTypeID;
        this.roomType = roomType;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoomTypeDTO{" + "roomTypeID=" + roomTypeID + ", roomType=" + roomType + ", price=" + price + ", description=" + description + '}';
    }
    
    
}
