package com.example.cours1android;

public class News {

    private String imageUrl;
    private String textResume;

    public String getTextResume() {
        return textResume;
    }
    public void setTextResume(String textResume) {
        this.textResume = textResume;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public News(String imageUrl, String textResume) {
        this.imageUrl = imageUrl;
        this.textResume = textResume;
    }
}
