/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CartItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class BookingDetailDAO {
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
    
    public boolean insert(int bookingID, CartItemDTO cart) throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblBookingDetail"
                + " (bookingID, roomID, price, checkInDate, checkOutDate)"
                + " VALUES (?,?,?,?,?)";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, bookingID);
            preStm.setInt(2, cart.getRoomID());
            preStm.setLong(3, cart.getPrice());
            preStm.setDate(4, java.sql.Date.valueOf(cart.getCheckInDate()));
            preStm.setDate(5, java.sql.Date.valueOf(cart.getCheckOutDate()));
            preStm.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return isSuccess;
    }
    
    public boolean rating(int bookingDetailID, int feedback) throws Exception{
        boolean isSuccess = false;
        String sql = "UPDATE tblBookingDetail"
                + " SET feedback = ?"
                + " WHERE bookingDetailID = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, feedback);
            preStm.setInt(2, bookingDetailID);
            preStm.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return isSuccess;
    }
}
