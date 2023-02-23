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
public class DiscountCodeDTO implements Serializable{
    private String discountID;
    private Integer percent;
    private Date expireDate;
    private Boolean isUsed;

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }
    
    
    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public DiscountCodeDTO() {
    }

    public DiscountCodeDTO(String discountID, Integer percent, Date expireDate) {
        this.discountID = discountID;
        this.percent = percent;
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "DiscountCodeDTO{" + "discountID=" + discountID + ", percent=" + percent + ", expireDate=" + expireDate + '}';
    }
    
    
}
