/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class DateUtils {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public int dayDiff(String txtCheckInDate, String txtCheckOutDate){
        int diffDays = -1;
        try {
            Date checkInDate = sdf.parse(txtCheckInDate);
            Date checkOutDate = sdf.parse(txtCheckOutDate);
            long diff = checkOutDate.getTime() - checkInDate.getTime();
            diffDays = (int) (diff / (1000*60*60*24));
            
        } catch (Exception e) {
        }
        return diffDays;
    }
    
    public String getCurrentDate(){
        Date date = new Date();
        return sdf.format(date);
    }
}
