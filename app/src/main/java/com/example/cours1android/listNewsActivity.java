package com.example.cours1android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class listNewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Source> listSources = new ArrayList<Source>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //On récupère la liste des sources
        new WebServiceRequestor(this, "https://newsapi.org/v2/sources?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr",params).execute();

        //On récupère la liste des articles pour une source
        new WebServiceRequestor(this, "https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources=google-news-fr",params).execute();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        System.out.println("Searching articles from "+parent.getItemAtPosition(pos));
        String complementUrl = new String ();

        for(int k=0; k<this.listSources.size();k++){
            if(parent.getItemAtPosition(pos).toString()==this.listSources.get(k).getName()){
                complementUrl=this.listSources.get(k).getIdUrl();
            }
        }
        if(!complementUrl.equals(new String())){
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            new WebServiceRequestor(this, "https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources="+complementUrl,params).execute();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("Nothing selected");
    }
}

class WebServiceRequestor extends AsyncTask<String, Void, String> {
    listNewsActivity activity;
    String URL;
    List<NameValuePair> parameters;

    public WebServiceRequestor(listNewsActivity activity, String url, List<NameValuePair> params)
    {
        this.activity=activity;
        this.URL = url;
        this.parameters = params;
    }
    @Override
    protected String doInBackground(String... params)
    {
        HttpURLConnection urlConnection = null;
        try {
            java.net.URL url = new URL(URL);

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            String result = new String();

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                result+=(current);
                data = isw.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "";
    }
    @Override
    protected void onPostExecute(String result)
    {

        //Parsing du result vers du format Json
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        if(this.URL.contains("/sources")) {{
            ArrayList<String> listSourcesName = new ArrayList<String>();
            ArrayList<Source> listSources = new ArrayList<Source>();
            for (int k = 0; k < jsonObject.get("sources").getAsJsonArray().size(); k++) {
                JsonObject jsonObjectForOneNews = jsonObject.get("sources").getAsJsonArray().get(k).getAsJsonObject();
                Source oneSource = new Source();
                oneSource.setIdUrl(jsonObjectForOneNews.get("id").getAsString());
                oneSource.setName(jsonObjectForOneNews.get("name").getAsString());
                listSourcesName.add(oneSource.getName());
                listSources.add(oneSource);
            }
            //Set des sources
            activity.listSources=listSources;
            Spinner spinner = (Spinner) activity.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(activity);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, listSourcesName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }}

        //Si on fait une requete sur /everything
        if(this.URL.contains("/everything")) {
            System.out.println("Refresh articles");
            final ArrayList<News> listNews = new ArrayList<News>();

            for (int k = 0; k < jsonObject.get("articles").getAsJsonArray().size(); k++) {
                //News oneNews = new Gson().fromJson(jsonObject.get("articles").getAsJsonArray().get(k), News.class);
                JsonObject jsonObjectForOneNews = jsonObject.get("articles").getAsJsonArray().get(k).getAsJsonObject();
                News oneNews = new News();
                oneNews.setTitre(jsonObjectForOneNews.get("title").getAsString());
                if (!jsonObjectForOneNews.get("author").isJsonNull()) {
                    oneNews.setAutor(jsonObjectForOneNews.get("author").getAsString());
                }
                oneNews.setDate(jsonObjectForOneNews.get("publishedAt").getAsString());
                if (!jsonObjectForOneNews.get("urlToImage").isJsonNull()) {
                    oneNews.setImageUrl(jsonObjectForOneNews.get("urlToImage").getAsString());
                }
                if (!jsonObjectForOneNews.get("url").isJsonNull()) {
                    oneNews.setUrlmatch(jsonObjectForOneNews.get("url").getAsString());
                }
                listNews.add(oneNews);
            }

            ListView myListView = (ListView) activity.findViewById(R.id.listView);
            NewsAdapter adapter = new NewsAdapter(activity, listNews);
            myListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String url = listNews.get(position).getUrlmatch();
                    if (url != null && !url.equals(new String())){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        view.getContext().startActivity(intent);
                    }
                }
            });
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}