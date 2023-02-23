/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class FeedBackDTO implements Serializable{
    private int feedBackID;
    private Integer rating;
    private String email;
    private Integer roomID;

    public FeedBackDTO() {
    }

    public FeedBackDTO(int feedBackID, Integer rating, String email, Integer roomID) {
        this.feedBackID = feedBackID;
        this.rating = rating;
        this.email = email;
        this.roomID = roomID;
    }

    public int getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(int feedBackID) {
        this.feedBackID = feedBackID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "FeedBackDTO{" + "feedBackID=" + feedBackID + ", rating=" + rating + ", email=" + email + ", roomID=" + roomID + '}';
    }
    
    
}
