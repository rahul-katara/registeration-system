package com.jedi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin extends User {

	private String adminId;
    private String instituteName;
    
    public Admin(String userId, String userName, String emailId, String password, String contactNo, String adminId, String instituteName) {
        super(userId, userName, emailId, password, contactNo);
        this.adminId = adminId;
        this.instituteName = instituteName;
    }

    @JsonProperty
    public String getAdminId() {
        return adminId;
    }
    
    @JsonProperty
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @JsonProperty
    public String getInstituteName() {
        return instituteName;
    }

    @JsonProperty
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
