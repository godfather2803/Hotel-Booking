/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.GooglePojo;
import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class UserDAO {
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
    
    public UserDTO getRole(UserDTO user) throws Exception{
        UserDTO rUser = new UserDTO();
        String sql = "SELECT tblRole.roleName, tblUser.status"
                + " FROM tblUser INNER JOIN tblRole"
                + " ON tblUser.roleID = tblRole.roleID"
                + " WHERE tblUser.status = ?"
                + " AND tblUser.email = ?"
                + " AND tblUser.password = ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, Boolean.TRUE);
            preStm.setString(2, user.getEmail());
            preStm.setString(3, user.getPassword());
            rs = preStm.executeQuery();
            if(rs.next()){
                rUser.setRoleName(rs.getString("roleName"));
                rUser.setStatus(rs.getString("status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return rUser;
    }
    
    public String loginWithGoogle(String email) throws Exception{
        String role = null;
        String sql = "SELECT tblRole.roleName "
                + " FROM tblUser INNER JOIN tblRole"
                + " ON tblUser.roleID = tblRole.roleID"
                + " WHERE tblUser.status = ?"
                + " AND tblUser.email = ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, Boolean.TRUE);
            preStm.setString(2, email);
            rs = preStm.executeQuery();
            if(rs.next()){
                role = rs.getString("roleName");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return role;
    }
    
    public boolean isExisted(String email) throws Exception{
        String sql = "SELECT email FROM tblUser WHERE email = ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    public boolean insert(UserDTO user) throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblUser"
                + " (email, password, roleID, phone, name,"
                + " address, createdDate, status)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user.getEmail());
            preStm.setString(2, user.getPassword());
            preStm.setInt(3, 2); //Role Customer
            preStm.setString(4, user.getPhone());
            preStm.setString(5, user.getName());
            preStm.setString(6, user.getAddress());
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preStm.setDate(7, sqlDate);
            preStm.setBoolean(8, true); //status active
            preStm.executeUpdate();
            isSuccess = true;
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return isSuccess;
    }
    
    public boolean insertGoogleUser(GooglePojo googleAcccount) throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblUser"
                + " (email, name, createdDate, roleID, status)"
                + " VALUES (?,?,?,?,?)";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, googleAcccount.getEmail());
            preStm.setString(2, googleAcccount.getName());
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preStm.setDate(3, sqlDate);
            preStm.setInt(4, 2);
            preStm.setBoolean(5, true); //status active
            preStm.executeUpdate();
            isSuccess = true;
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return isSuccess;
    }
}
