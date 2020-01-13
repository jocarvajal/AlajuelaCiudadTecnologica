package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private String email, password;
    private boolean guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        email = getIntent().getStringExtra("email");
        guest = getIntent().getBooleanExtra("invitado",false);
        password = getIntent().getStringExtra("contrasena");
    }

    public void  config(View v){
        if(!guest){
            Intent i = new Intent(Menu.this, Configuration.class);
            i.putExtra("email",email);
            i.putExtra("contrasena",password);
            Menu.this.startActivity(i);
            Menu.this.finish();
        }else{
            Toast.makeText(Menu.this,"No disponible en modo invitado",Toast.LENGTH_SHORT).show();
        }

    }


    public void  show_bus(View v){
        Intent i = new Intent(Menu.this, ShowMap.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }

    public void  show_local(View v){
        Intent i = new Intent(Menu.this, ShowMap.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }

    public void  show_turist(View v){
        Intent i = new Intent(Menu.this, ShowMap.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }

    public void  show_all(View v){
        Intent i = new Intent(Menu.this, ShowMap.class);
        Menu.this.startActivity(i);
        Menu.this.finish();
    }

    public void  show_institutions(View v){
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
