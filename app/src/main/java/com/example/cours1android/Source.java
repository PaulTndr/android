package com.example.cours1android;

import java.io.Serializable;

public class Source implements Serializable {
    private String idUrl;
    private String name;

    public String getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(String idUrl) {
        this.idUrl = idUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
