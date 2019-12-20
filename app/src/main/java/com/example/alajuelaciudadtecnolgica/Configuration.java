package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }

    public void returning(View v){
        Intent i = new Intent(Configuration.this, Menu.class);
        Configuration.this.startActivity(i);
        Configuration.this.finish();

    }
}
