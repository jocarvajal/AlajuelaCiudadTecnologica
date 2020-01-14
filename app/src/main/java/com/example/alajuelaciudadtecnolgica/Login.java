package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private EditText email_database ;
    private EditText password_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView register = (TextView)findViewById(R.id.tregister);
        TextView guest = (TextView)findViewById(R.id.tinvi);
        email_database = (EditText)findViewById(R.id.edmail);
        password_database = (EditText)findViewById(R.id.edpass);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_activity = new Intent(Login.this, Register.class);
                Login.this.startActivity(register_activity);
                finish();
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu_activity = new Intent(Login.this, Menu.class);
                menu_activity.putExtra("invitado",true);
                Login.this.startActivity(menu_activity);
                finish();
            }
        });
    }

    public void startSession(View v){
        String user = email_database.getText().toString();
        String password = password_database.getText().toString();
        Response.Listener<String> answer = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean ok = jsonResponse.getBoolean("success");
                    if(ok){
                        Intent menu = new Intent(Login.this, Menu.class);
                        menu.putExtra("invitado",false);
                        menu.putExtra("email",email_database.getText().toString());
                        menu.putExtra("contrasena",password_database.getText().toString());
                        Login.this.startActivity(menu);
                        Login.this.finish();
                    }else{
                        Toast.makeText(Login.this,"El usuario no existe o su contraseña es incorrecta",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
                        alert.setMessage("Fallo en inicio de sesión").setNegativeButton("Reintentar", null)
                                .create()
                                .show();
                }
                }catch (JSONException e){
                    e.getMessage();
                }
            }
        };
        LoginRequest r = new LoginRequest(user, password,answer);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(r);
    }
}
