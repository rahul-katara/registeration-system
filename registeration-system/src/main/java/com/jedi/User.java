package com.jedi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private String userId;
    private String userName;
    private String emailId;
    private String password;
    private String contactNo;

    public User(){
    	
    }
    public User(String userId, String userName, String emailId, String password, String contactNo) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.contactNo = contactNo;
    }

    @JsonProperty
    public String getUserId() {
        return userId;
    }

    @JsonProperty
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty
    public String getUserName() {
        return userName;
    }

    @JsonProperty
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty
    public String getEmailId() {
        return emailId;
    }

    @JsonProperty
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public String getContactNo() {
        return contactNo;
    }

    @JsonProperty
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
