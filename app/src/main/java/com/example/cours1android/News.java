package com.example.cours1android;

public class News {

    private String imageUrl;
    private String titre;
    private String autor;
    private String date;
    private String urlMatch;

    public News(){}

    public News(String imageUrl, String textResume, String autor, String date, String urlmatch) {
        this.imageUrl = imageUrl;
        this.titre = textResume;
        this.autor = autor;
        this.date = date;
        this.urlMatch = urlmatch;
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

    public void print() {
       System.out.println("News{" +
               "imageUrl='" + imageUrl + '\'' +
               ", titre='" + titre + '\'' +
               ", autor='" + autor + '\'' +
               ", date='" + date + '\'' +
               ", urlMatch='" + urlMatch + '\'' +
               '}');
    }
}
