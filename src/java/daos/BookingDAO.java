/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.BookingDTO;
import dtos.BookingDetailDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtilities;
import utils.DateUtils;

/**
 *
 * @author DELL
 */
public class BookingDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public ArrayList<BookingDTO> getBookingHistory(String email) throws Exception {
       ArrayList<BookingDTO> listBooking = new ArrayList<>();
       String sql = "SELECT b.bookingID, b.email, b.totalPrice, b.bookingDate, b.verified," +
                    " bd.roomID, bd.price, bd.checkInDate, bd.checkOutDate, bd.feedback, bd.bookingDetailID" +
                    " FROM tblBooking b" +
                    " INNER JOIN tblBookingDetail bd on b.bookingID = bd.bookingID" +
                    " WHERE b.email = ?" +
                    " AND status = ?" +
                    " ORDER BY b.bookingID DESC";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            int currentBookingID = 0;
            int currentIndex = -1;
            ArrayList<BookingDetailDTO> listBookingDetail = new ArrayList<>();
            while(rs.next()){
                int bookingID = rs.getInt("bookingID");
                Long totalPrice = rs.getLong("totalPrice");
                Date bookingDate = rs.getDate("bookingDate");
                Boolean verified = rs.getBoolean("verified");
                BookingDetailDTO bookingDetail = new BookingDetailDTO();
                bookingDetail.setBookingDetailID(rs.getInt("bookingDetailID"));
                bookingDetail.setRoomID(rs.getInt("roomID"));
                bookingDetail.setPrice(rs.getLong("price"));
                bookingDetail.setCheckInDate(rs.getDate("checkInDate"));
                bookingDetail.setCheckOutDate(rs.getDate("checkOutDate"));
                bookingDetail.setFeedback(rs.getInt("feedback"));
                
                if(bookingDetail.getFeedback() == 0){
                    DateUtils dateUtils = new DateUtils();
                    String today = dateUtils.getCurrentDate();
                    String checkOutDate = bookingDetail.getCheckOutDate().toString();
                    int dayDiffs = dateUtils.dayDiff(checkOutDate, today);
                    if(dayDiffs > 0){
                        bookingDetail.setIsFeedbackAble(true);
                    } else{
                        bookingDetail.setIsFeedbackAble(false);
                    }
                }
                
                
                //tạo 1 booking và listBookingDetail mới nếu bookingDetail này không thuộc booking hiện tại (currentBookingID)
                if(currentBookingID == 0 || currentBookingID != bookingID){
                    currentBookingID = bookingID;
                    BookingDTO booking = new BookingDTO();
                    booking.setBookingID(bookingID);
                    booking.setTotalPrice(totalPrice);
                    booking.setBookingDate(bookingDate);
                    booking.setVerified(verified);
                    
                    listBookingDetail = new ArrayList<>();
                    listBookingDetail.add(bookingDetail);
                    
                    booking.setBookingDetail(listBookingDetail);
                    
                    listBooking.add(booking);
                    currentIndex += 1;
                }
                
                //add bookingDetail vào booking trước
                else if(currentBookingID != 0 && currentBookingID == bookingID){
                    
                    listBookingDetail.add(bookingDetail);
                    listBooking.get(currentIndex).setBookingDetail(listBookingDetail);
                }
            }
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
       return listBooking;
    }
    
    
    
    
    
    public int insert(String email, Long totalPrice) throws Exception {
       int insertedID = 0;
       String sql = "INSERT INTO tblBooking (email, totalPrice, bookingDate, status, verified)" +
                    " OUTPUT Inserted.bookingID" +
                    " VALUES (?,?,?,?,?)";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setLong(2, totalPrice);
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preStm.setDate(3, sqlDate);
            preStm.setBoolean(4, true);
            preStm.setBoolean(5, false);
            rs = preStm.executeQuery();
            if(rs.next()){
                insertedID = rs.getInt("bookingID");
            }
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
       return insertedID;
    }
    
    
    public ArrayList<BookingDTO> getBookingHistoryByDates(String email, String from, String to) throws Exception {
       ArrayList<BookingDTO> listBooking = new ArrayList<>();
       String sql = "SELECT b.bookingID, b.email, b.totalPrice, b.bookingDate, b.verified," +
                    " bd.roomID, bd.price, bd.checkInDate, bd.checkOutDate, bd.feedback, bd.bookingDetailID" +
                    " FROM tblBooking b" +
                    " INNER JOIN tblBookingDetail bd on b.bookingID = bd.bookingID" +
                    " WHERE b.email = ?" +
                    " AND status = ?" +
                    " AND bookingDate >= ?" +
                    " AND bookingDate <= ?" +
                    " ORDER BY b.bookingID DESC";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setBoolean(2, true);
            preStm.setDate(3, java.sql.Date.valueOf(from));
            preStm.setDate(4, java.sql.Date.valueOf(to));
            rs = preStm.executeQuery();
            int currentBookingID = 0;
            int currentIndex = -1;
            ArrayList<BookingDetailDTO> listBookingDetail = new ArrayList<>();
            while(rs.next()){
                int bookingID = rs.getInt("bookingID");
                Long totalPrice = rs.getLong("totalPrice");
                Date bookingDate = rs.getDate("bookingDate");
                Boolean verified = rs.getBoolean("verified");
                BookingDetailDTO bookingDetail = new BookingDetailDTO();
                bookingDetail.setBookingDetailID(rs.getInt("bookingDetailID"));
                bookingDetail.setRoomID(rs.getInt("roomID"));
                bookingDetail.setPrice(rs.getLong("price"));
                bookingDetail.setCheckInDate(rs.getDate("checkInDate"));
                bookingDetail.setCheckOutDate(rs.getDate("checkOutDate"));
                bookingDetail.setFeedback(rs.getInt("feedback"));
                
                if(bookingDetail.getFeedback() == 0){
                    DateUtils dateUtils = new DateUtils();
                    String today = dateUtils.getCurrentDate();
                    String checkOutDate = bookingDetail.getCheckOutDate().toString();
                    int dayDiffs = dateUtils.dayDiff(checkOutDate, today);
                    if(dayDiffs > 0){
                        bookingDetail.setIsFeedbackAble(true);
                    } else{
                        bookingDetail.setIsFeedbackAble(false);
                    }
                }
                
                //tạo 1 booking và listBookingDetail mới nếu bookingDetail này không thuộc booking hiện tại (currentBookingID)
                if(currentBookingID == 0 || currentBookingID != bookingID){
                    currentBookingID = bookingID;
                    BookingDTO booking = new BookingDTO();
                    booking.setBookingID(bookingID);
                    booking.setTotalPrice(totalPrice);
                    booking.setBookingDate(bookingDate);
                    booking.setVerified(verified);
                    
                    listBookingDetail = new ArrayList<>();
                    listBookingDetail.add(bookingDetail);
                    
                    booking.setBookingDetail(listBookingDetail);
                    
                    listBooking.add(booking);
                    currentIndex += 1;
                }
                
                //add bookingDetail vào booking trước
                else if(currentBookingID != 0 && currentBookingID == bookingID){
                    
                    listBookingDetail.add(bookingDetail);
                    listBooking.get(currentIndex).setBookingDetail(listBookingDetail);
                }
            }
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
       return listBooking;
    }
    
    public int verifyBooking(int bookingID) throws Exception {
       int affected = 0;
       String sql = "UPDATE tblBooking SET verified = ?"
               + " WHERE bookingID = ?"
               + " AND verified = ?"
               + " AND status = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setInt(2, bookingID);
            preStm.setBoolean(3, false);
            preStm.setBoolean(4, true);
            affected = preStm.executeUpdate();
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
       return affected;
    }
    
    
    public boolean isExisted(int bookingID) throws Exception {
       boolean isExisted = false;
       String sql = "SELECT bookingID FROM tblBooking WHERE bookingID = ?"
               + " AND status = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, bookingID);
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            if(rs.next()){
               isExisted = true;
            }
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
       return isExisted;
    }
    
    public boolean deleteBookingHistory(int bookingID) throws Exception {
       boolean isSuccess = false;
       String sql = "UPDATE tblBooking SET status = ?"
               + " WHERE bookingID = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, false);
            preStm.setInt(2, bookingID);
            preStm.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
       return isSuccess;
    }
}
