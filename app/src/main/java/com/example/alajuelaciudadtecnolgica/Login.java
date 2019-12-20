package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView register = (TextView)findViewById(R.id.tregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_activity = new Intent(Login.this, Register.class);
                Login.this.startActivity(register_activity);
                finish();
            }
        });
    }

    public void startSession(View v){
        Intent menu = new Intent(Login.this, Menu.class);
        Login.this.startActivity(menu);
        Login.this.finish();
    }
}
