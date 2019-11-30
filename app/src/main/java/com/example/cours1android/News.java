package com.example.cours1android;

import java.io.Serializable;

public class News implements Serializable {

    private String imageUrl;
    private String titre;
    private String autor;
    private String date;
    private String urlMatch;
    private Source source;
    private String description;

    public News(){}

    public News(String imageUrl, String textResume, String autor, String date, String urlmatch) {
        this.imageUrl = imageUrl;
        this.titre = textResume;
        this.autor = autor;
        this.date = date;
        this.urlMatch = urlmatch;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrlmatch() {
        return urlMatch;
    }

    public void setUrlmatch(String urlMatch) {
        this.urlMatch = urlMatch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void print() {
       System.out.println("News{" +
               "imageUrl='" + imageUrl + '\'' +
               ", titre='" + titre + '\'' +
               ", autor='" + autor + '\'' +
               ", date='" + date + '\'' +
               ", urlMatch='" + urlMatch + '\'' +
               ", description='" + description + '\'' +
               '}');
    }
}
