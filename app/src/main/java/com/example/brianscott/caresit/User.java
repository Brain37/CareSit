package com.example.brianscott.caresit;

/**
 * Created by Brian Scott on 4/12/2016.
 */
public class User
{
    private String username;
    private String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }
}
