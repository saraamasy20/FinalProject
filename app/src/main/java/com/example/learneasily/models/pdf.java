package com.example.learneasily.models;

public class pdf {

   public String name,url,id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public pdf() {
    }

    public pdf(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public pdf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
}
