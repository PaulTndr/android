package com.example.cours1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Launching");
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent monIntent = new Intent(MainActivity.this, listNewsActivity.class);
                startActivity(monIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
