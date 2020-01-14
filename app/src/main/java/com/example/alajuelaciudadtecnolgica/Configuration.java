package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Configuration extends AppCompatActivity {

    private EditText name;
    private EditText last_name_1;
    private EditText last_name_2;
    private EditText birthday;
    private EditText old_password;
    private EditText new_password;
    private EditText check_password;
    private CheckBox notifications;
    private String email,actual_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        email = getIntent().getStringExtra("email");
        actual_password = getIntent().getStringExtra("contrasena");
        name = (EditText)findViewById(R.id.edname);
        last_name_1 = (EditText)findViewById(R.id.edlast1);
        last_name_2 = (EditText)findViewById(R.id.edlast2);
        birthday = (EditText)findViewById(R.id.eddate);
        old_password = (EditText)findViewById(R.id.edoldpass);
        new_password = (EditText)findViewById(R.id.edpass);
        check_password = (EditText)findViewById(R.id.edcheckpass);
        notifications = (CheckBox)findViewById(R.id.cbnoti);
    }

    public void update_user(View v)throws Exception{
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        String name_database = name.getText().toString();
        String last_name_1_databse = last_name_1.getText().toString();
        String last_name_2_databse = last_name_2.getText().toString();
        Date birthday_database = formatter1.parse(birthday.getText().toString());
        String old_password_database = old_password.getText().toString();
        String password_database = new_password.getText().toString();
        String check_password_database = check_password.getText().toString();
        Boolean checkbox_database = notifications.isSelected();

        if (!password_database.equals(check_password_database)){
            Toast.makeText(Configuration.this, "No coinciden contraseñas", Toast.LENGTH_SHORT).show();
        }else{
            Response.Listener<String> answer = new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    try{
                        JSONObject jsonAnswer = new JSONObject(response);
                        boolean correct = jsonAnswer.getBoolean("success");
                        if (correct){
                            Toast.makeText(Configuration.this,"Actualizado correctamente",Toast.LENGTH_SHORT).show();
                            Intent c = new Intent(Configuration.this, Menu.class);
                            Configuration.this.startActivity(c);
                            Configuration.this.finish();
                        }else{
                            AlertDialog.Builder alert = new AlertDialog.Builder(Configuration.this);
                            alert.setMessage("Fallo actualizando").setNegativeButton("Reintentar", null)
                                    .create()
                                    .show();
                        }
                    }catch(JSONException e){
                        e.getMessage();
                    }
                }
            };
            String persisted_password;
            if(password_database.equals("")){
                persisted_password = actual_password;
            }else{
                persisted_password = password_database;
            }

            ConfigRequest r;
            r = new ConfigRequest(name_database, last_name_1_databse, last_name_2_databse, birthday_database, old_password_database, persisted_password, checkbox_database,email, answer);
            RequestQueue queue = Volley.newRequestQueue(Configuration.this);
            queue.add(r);
        }

    }

    public void returning(View v){
        Intent i = new Intent(Configuration.this, Menu.class);
        Configuration.this.startActivity(i);
        Configuration.this.finish();

    }
}
