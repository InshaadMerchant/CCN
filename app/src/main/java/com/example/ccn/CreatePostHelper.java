package com.example.ccn;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CreatePostHelper extends CreatePost{
    public String title;
    public String description;

    public CreatePostHelper(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public CreatePostHelper() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
}
