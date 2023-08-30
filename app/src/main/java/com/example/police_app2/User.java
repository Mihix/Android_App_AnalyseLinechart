package com.example.police_app2;



import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name;
    private String licenseNumber;
    private String details;
    private String money;

    public User() {
    }

    public User(String name, String licenseNumber, String details, String money) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.details = details;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getDetails() {
        return details;
    }

    public String getMoney() {
        return money;
    }
}
