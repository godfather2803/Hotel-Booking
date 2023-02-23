/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class BookingDTO implements Serializable{
    private int bookingID;
    private String email;
    private Long totalPrice;
    private Date bookingDate;
    private ArrayList<BookingDetailDTO> bookingDetail;
    private Boolean verified;

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    
    public BookingDTO() {
    }

    public BookingDTO(int bookingID, String email, Long totalPrice, Date bookingDate, ArrayList<BookingDetailDTO> bookingDetail) {
        this.bookingID = bookingID;
        this.email = email;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
        this.bookingDetail = bookingDetail;
    }

    public BookingDTO(int bookingID, String email, Long totalPrice, Date bookingDate) {
        this.bookingID = bookingID;
        this.email = email;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public ArrayList<BookingDetailDTO> getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(ArrayList<BookingDetailDTO> bookingDetail) {
        this.bookingDetail = bookingDetail;
    }

    @Override
    public String toString() {
        return "BookingDTO{" + "bookingID=" + bookingID + ", email=" + email + ", totalPrice=" + totalPrice + ", bookingDate=" + bookingDate + ", bookingDetail=" + bookingDetail + '}';
    }

    
    
    
}
