package com.example.ccn;

public class Model {
    String title, contents, location;
    public Model(){}
    public Model(String title, String contents, String location)
    {
        this.title=title;
        this.contents= contents;
        this.location = location;
    }
    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getLocation() {
        return location;
    }
    public void setTitle(String title)
    {
        this.title= title;
    }
    public void setContents(String contents)
    {
        this.contents=contents;
    }
}
