package com.adsi5226.popayanturims.Modelo;

public class User {

    public User() {
    }

    private String id;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String email_verified_a;
    private String Avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_verified_a() {
        return email_verified_a;
    }

    public void setEmail_verified_a(String email_verified_a) {
        this.email_verified_a = email_verified_a;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
}
