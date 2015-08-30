package com.example.io.io.models;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class User {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;

    public User(
        final String newFirstName,
        final String newLastName,
        final String newStreet,
        final String newCity,
        final String newZip)
    {
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.street = newStreet;
        this.city = newCity;
        this.zip = newZip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }
}
