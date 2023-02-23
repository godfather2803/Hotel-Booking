/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class BookingDetailDTO implements Serializable{
    private int bookingDetailID;
    private int roomID;
    private Long price;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer feedback;
    private Boolean isFeedbackAble;

    public int getBookingDetailID() {
        return bookingDetailID;
    }

    public void setBookingDetailID(int bookingDetailID) {
        this.bookingDetailID = bookingDetailID;
    }

    public Boolean getIsFeedbackAble() {
        return isFeedbackAble;
    }

    public void setIsFeedbackAble(Boolean isFeedbackAble) {
        this.isFeedbackAble = isFeedbackAble;
    }
    
    

    public BookingDetailDTO() {
    }

    public Integer getFeedback() {
        return feedback;
    }

    public void setFeedback(Integer feedback) {
        this.feedback = feedback;
    }

    
    
    public BookingDetailDTO(int roomID, Long price, Date checkInDate, Date checkOutDate) {
        this.roomID = roomID;
        this.price = price;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "BookingDetailDTO{" + "roomID=" + roomID + ", price=" + price + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", feedback=" + feedback + ", isFeedbackAble=" + isFeedbackAble + '}';
    }

    
    
    
}
