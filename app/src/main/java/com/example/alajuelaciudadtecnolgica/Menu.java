package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void  config(View v){
        Intent i = new Intent(Menu.this, Configuration.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }



    public void  show_bus(View v){
        Intent i = new Intent(Menu.this, ShowMap.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }

    public void  returning(View v){
        Intent i = new Intent(Menu.this, Login.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }
}
