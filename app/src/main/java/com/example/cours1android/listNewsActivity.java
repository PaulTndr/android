package com.example.cours1android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

public class listNewsActivity extends AppCompatActivity {

    List<News> sourcesNews = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Button button1 = (Button) findViewById(R.id.buttondata);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        new WebServiceRequestor(this, "https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources=google-news-fr",params).execute();
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

        System.out.println(result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        //JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);

        ArrayList<News> listNews = new ArrayList<News>();

        for (int k=0; k<jsonObject.get("articles").getAsJsonArray().size(); k++){
            //News oneNews = new Gson().fromJson(jsonObject.get("articles").getAsJsonArray().get(k), News.class);
            JsonObject jsonObjectForOneNews = jsonObject.get("articles").getAsJsonArray().get(k).getAsJsonObject();
            News oneNews = new News();
            oneNews.setTitre(jsonObjectForOneNews.get("title").getAsString());
            if(!jsonObjectForOneNews.get("author").isJsonNull()){
                oneNews.setAutor(jsonObjectForOneNews.get("author").getAsString());
            }
            oneNews.setDate(jsonObjectForOneNews.get("publishedAt").getAsString());
            if(!jsonObjectForOneNews.get("urlToImage").isJsonNull()){
                oneNews.setImageUrl(jsonObjectForOneNews.get("urlToImage").getAsString());
            }

            oneNews.print();
            listNews.add(oneNews);
        }

        ListView myListView = (ListView) activity.findViewById(R.id.listView);
        NewsAdapter adapter = new NewsAdapter(activity, listNews);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        super.onPostExecute(result);
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
