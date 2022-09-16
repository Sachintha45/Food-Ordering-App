package com.example.flamespizzeria;

public class User {
    private String Phone;
    private String Password;
    private String Name;
    private String Address;
    private String Email;

    public User(){

    }

    public User(String phone, String password, String name, String address, String email) {
        Phone = phone;
        Password = password;
        Name = name;
        Address = address;
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
