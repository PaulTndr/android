package com.example.cours1android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class listNewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Button button1 = (Button) findViewById(R.id.buttondata);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                new
                        WebServiceRequestor("https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources=google-news-fr",params).execute();
            }
        });
    }
}

class WebServiceRequestor extends AsyncTask<String, Void, String> {
    private ProgressDialog pDialog;
    String URL;
    List<NameValuePair> parameters;
    public WebServiceRequestor(String url, List<NameValuePair> params)
    {
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

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
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
        /*pDialog.dismiss();
        TextView txt = (TextView) findViewById(R.id.resulttxt);
// txt.setText("Response is: "+ result.substring(0,500)); // txt.setText(result);
        try {
            JSONObject theObject = new JSONObject(result);
            txt.setText("Response is: "+theObject.getString("status")+"\n"+
                    theObject.getString("count")+"/"+theObject.getString("count_total"));
        } catch (Exception e){
            txt.setText("Error during process"); // txt.setText(result);
        }*/
        System.out.println(result);
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
