package com.database.aim.pojo;

public class BriefUser {
    private int id;
    private String username;
    private Authority authority;
    public BriefUser(int id, String name, Authority authority) {
        this.authority = authority;
        this.id = id;
        this.username = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
