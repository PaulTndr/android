package com.example.cours1android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailNewsActivity extends AppCompatActivity {
    private News mNews;
    private String sourceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);
        mNews = (News) getIntent().getSerializableExtra("mNews");

        ((TextView) findViewById(R.id.titre)).setText(mNews.getTitre());
        ((TextView) findViewById(R.id.date)).setText(mNews.getDate());
        ((TextView) findViewById(R.id.auteur)).setText(mNews.getAutor());
        ((TextView) findViewById(R.id.source)).setText(mNews.getSource().getName());
        ((TextView) findViewById(R.id.description)).setText(mNews.getDescription());
        if(mNews.getImageUrl()!=null && !mNews.getImageUrl().equals(new String())){
            Picasso.with(this).load(mNews.getImageUrl()).into((ImageView) findViewById(R.id.illustration));
        }

        Button btn = (Button)findViewById(R.id.read);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = mNews.getUrlmatch();
                if (url != null && !url.equals(new String())){
                    Intent intent = new Intent(DetailNewsActivity.this, WebViewNewsActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);*/

                }
            }
        });

    }
}
