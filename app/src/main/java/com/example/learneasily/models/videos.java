package com.example.learneasily.models;

import android.net.Uri;

public class videos {
    String title, id;
String url;
    public videos() {
    }

    public videos(String title, String id, String url) {
        this.title = title;
        this.id = id;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

