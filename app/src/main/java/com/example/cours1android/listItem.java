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

        ListView myListView = (ListView) findViewById(R.id.listView);
        NewsAdapter adapter = new NewsAdapter(this, sourcesNews);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
