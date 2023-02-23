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
public class UserDTO implements Serializable{
    private String email;
    private String password;
    private String roleName;
    private int roleID;
    private String phone;
    private String name;
    private String address;
    private Date createdDate;
    private String status;

    public UserDTO() {
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public UserDTO(String email, String password, String phone, String name, String address) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.address = address;
    }

    
    
    public UserDTO(String email, String password, int roleID, String phone, String name, String address, Date createdDate, String status) {
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.status = status;
    }

    
    
    public UserDTO(String email, String password, String roleName, String phone, String name, String address, Date createdDate, String status) {
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.status = status;
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "UserDTO{" + "email=" + email + ", password=" + password + ", roleName=" + roleName + ", phone=" + phone + ", name=" + name + ", address=" + address + ", createdDate=" + createdDate + ", status=" + status + '}';
    }
    
    
}
