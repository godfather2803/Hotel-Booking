/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RoomDTO;
import dtos.RoomTypeDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class RoomTypeDAO implements Serializable{
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
    
    public ArrayList<RoomTypeDTO> getAll()throws Exception{
        ArrayList<RoomTypeDTO> roomTypeList = new ArrayList<>();
        String sql = "SELECT tblRoomType.roomTypeID, tblRoomType.roomType,"
                + " tblRoomType.price, tblRoomType.description"
                + " FROM tblRoomType";
        
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()){
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setRoomTypeID(rs.getInt("roomTypeID"));
                roomType.setRoomType(rs.getString("roomType"));
                roomType.setPrice(rs.getLong("price"));
                roomType.setDescription("description");
                roomTypeList.add(roomType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return roomTypeList;
    }
}
