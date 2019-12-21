package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private EditText edemail ;
    private EditText edpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView register = (TextView)findViewById(R.id.tregister);
        edemail = (EditText)findViewById(R.id.edmail);
        edpassword = (EditText)findViewById(R.id.edpass);
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
        String user = edemail.getText().toString();
        String password = edpassword.getText().toString();
        Response.Listener<String> answer = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean ok = jsonResponse.getBoolean("success");
                    if(ok){
                        Intent menu = new Intent(Login.this, Menu.class);
                        Login.this.startActivity(menu);
                        Login.this.finish();
                    }else{
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

    }
}
