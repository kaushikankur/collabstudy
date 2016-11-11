package com.example.chirag.rustuck;

/**
 * Created by CodeIsLife on 10-11-2016.
 */
public class ContactData {

    int id;
    String name;
    String email;
    String password;
    String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContactData(String name, String email, String password,String phoneNumber) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber=phoneNumber;
    }

    public ContactData() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
