package com.example.cours1android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailNewsActivity extends AppCompatActivity {
    private News mNews;
    private String sourceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);
        mNews = (News) getIntent().getSerializableExtra("mNews");
        System.out.println(mNews.getTitre());

        ((TextView) findViewById(R.id.titre)).setText(mNews.getTitre());
        ((TextView) findViewById(R.id.date)).setText(mNews.getDate());
        ((TextView) findViewById(R.id.auteur)).setText(mNews.getAutor());
        ((TextView) findViewById(R.id.source)).setText(mNews.getSource().getName());
        ((TextView) findViewById(R.id.description)).setText(mNews.getDescription());
    }
}
