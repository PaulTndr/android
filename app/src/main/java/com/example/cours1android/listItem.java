package com.example.cours1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listItem extends AppCompatActivity {

    List<News> sourcesNews = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        this.fillNews();
        ListView myListView = (ListView) findViewById(R.id.listView);
        NewsAdapter adapter = new NewsAdapter(this, sourcesNews);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void fillNews(){
        News oneNews1=new News("imageUrl 1", "Un texte de résumé 1");
        News oneNews2=new News("imageUrl 2", "Un texte de résumé 2");
        News oneNews3=new News("imageUrl 3", "Un texte de résumé 3");
        News oneNews4=new News("imageUrl 4", "Un texte de résumé 4");
        News oneNews5=new News("imageUrl 5", "Un texte de résumé 5");

        this.sourcesNews.add(oneNews1);
        this.sourcesNews.add(oneNews2);
        this.sourcesNews.add(oneNews3);
        this.sourcesNews.add(oneNews4);
        this.sourcesNews.add(oneNews5);

    }
}
