package com.example.io.io.patterns;

import com.example.io.io.models.User;

public class UserBuilder
{
    private String newFirstName;
    private String newLastName;
    private String newStreet;
    private String newCity;
    private String newZip;

    public UserBuilder()
    {
    }

    public UserBuilder setNewLastName(String newLastName) {
        this.newLastName = newLastName;
        return this;
    }

    public UserBuilder setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
        return this;
    }

    public UserBuilder setNewStreet(String newStreet) {
        this.newStreet = newStreet;
        return this;
    }

    public UserBuilder setNewCity(String newCity) {
        this.newCity = newCity;
        return this;
    }

    public UserBuilder setNewZip(String newZip) {
        this.newZip = newZip;
        return this;
    }

    public User createPerson() {
        return new User(newLastName, newFirstName, newStreet, newCity, newZip);
    }

}
