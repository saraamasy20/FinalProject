package com.example.learneasily.models;

import android.net.Uri;

public class videos {
    String title, id;
     Uri url;
    public videos() {
    }

    public videos(String title, String id, Uri url) {
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

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }


}

