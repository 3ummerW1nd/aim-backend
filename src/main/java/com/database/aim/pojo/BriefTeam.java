package com.database.aim.pojo;

public class BriefTeam {
    private int id;
    private String name;
    private Authority authority;

    public BriefTeam(int id, String name, Authority authority) {
        this.authority = authority;
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
