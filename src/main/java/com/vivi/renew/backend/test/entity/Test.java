package com.vivi.renew.backend.test.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public Test() {
        this.userID = 1;
        this.userName = "Jack";
        this.userAge = 20;
        this.url = "";
        books = new ArrayList<>();
        books.add("English");
        books.add("Mathetics");
    }

    private int userID;
    private String userName;
    private int userAge;
    private String url;
    private List<String> books;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
