package com.ticket.code.tickets.login.model;

public class AuthResponse {

    private Long userID;
    private String userName;
    private String name;
    private String email;
    private String role;
    private Long areaID;
    private String areaName;

    public AuthResponse(
            Long userID,
            String userName,
            String name,
            String email,
            String role,
            Long areaID,
            String areaName) {

        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.role = role;
        this.areaID = areaID;
        this.areaName = areaName;
    }

    public Long getUserID() { return userID; }
    public String getUserName() { return userName; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public Long getAreaID() { return areaID; }
    public String getAreaName() { return areaName; }
}
