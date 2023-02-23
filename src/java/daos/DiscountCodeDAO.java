/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.DiscountCodeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class DiscountCodeDAO {
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
    
    public boolean insert(DiscountCodeDTO discountCode)throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblDiscountCode"
                + " (tblDiscountCode.discountID, tblDiscountCode.expiredDate, tblDiscountCode.[percent])"
                + " VALUES (?,?,?)";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountCode.getDiscountID());
            preStm.setDate(2, discountCode.getExpireDate());
            preStm.setInt(3, discountCode.getPercent());
            preStm.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return isSuccess;
    }
    
    public DiscountCodeDTO isExist(String discountID)throws Exception{
        DiscountCodeDTO discount = new DiscountCodeDTO();
        String sql = "SELECT [percent], expiredDate FROM tblDiscountCode"
                +" WHERE discountID = ?"
                +" AND expiredDate >= ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountID);
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preStm.setDate(2, sqlDate);
            rs = preStm.executeQuery();
            if(rs.next()){
                discount.setDiscountID(discountID);
                discount.setPercent(rs.getInt("percent"));
                discount.setExpireDate(rs.getDate("expiredDate"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return discount;
    }
    
    
    public Boolean isUsed(String discountID, String email) throws Exception{
        Boolean isUsed = false;
        String sql = "SELECT isUsed FROM tblUserDiscount"
                +" WHERE discountID = ?"
                +" AND email = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountID);
            preStm.setString(2, email);
            rs = preStm.executeQuery();
            if(rs.next()){
                isUsed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return isUsed;
    }
    
    public boolean use(String discountID, String email)throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblUserDiscount (email, discountID, isUsed)"
                   + " VALUES(?,?,?)";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, discountID);
            preStm.setBoolean(3, true);
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
