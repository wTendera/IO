package com.example.io.io;

import android.test.AndroidTestCase;

import com.example.io.io.models.User;
import com.example.io.io.patterns.UserBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class UserBuilderTest extends AndroidTestCase {
    public void setUp() {
        UserBuilder builder = new UserBuilder();
        builder.setNewFirstName("Marian");
        builder.setNewLastName("Pazdzioch");
        builder.setNewCity("Krakow");
        builder.setNewStreet("Krakowska 10");
        builder.setNewZip("31-011");
        User u =builder.createPerson();
        assertEquals(u.getFirstName(), "Marian");
        assertEquals(u.getLastName(), "Pazdzioch");
        assertEquals(u.getCity(), "Krakow");
        assertEquals(u.getStreet(), "Krakowska 10");
        assertEquals(u.getZip(), "31-011");
    }


    public void tearDown() throws Exception{
        super.tearDown();
    }


}


