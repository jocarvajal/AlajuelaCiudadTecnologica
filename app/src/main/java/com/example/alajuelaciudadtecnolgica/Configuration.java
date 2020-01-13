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
    private EditText last1;
    private EditText last2;
    private EditText date;
    private EditText oldpass;
    private EditText newpass;
    private EditText checkpass;
    private CheckBox notifications;
    private String email,actualpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        email = getIntent().getStringExtra("email");
        actualpass = getIntent().getStringExtra("contrasena");
        name = (EditText)findViewById(R.id.edname);
        last1 = (EditText)findViewById(R.id.edlast1);
        last2 = (EditText)findViewById(R.id.edlast2);
        date = (EditText)findViewById(R.id.eddate);
        oldpass = (EditText)findViewById(R.id.edoldpass);
        newpass = (EditText)findViewById(R.id.edpass);
        checkpass = (EditText)findViewById(R.id.edcheckpass);
        notifications = (CheckBox)findViewById(R.id.cbnoti);
    }

    public void update_user(View v)throws Exception{
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        String dname = name.getText().toString();
        String dlast1 = last1.getText().toString();
        String dlast2 = last2.getText().toString();
        Date ddate = formatter1.parse(date.getText().toString());
        String doldpass = oldpass.getText().toString();
        String dpass = newpass.getText().toString();
        String dcheck_pass = checkpass.getText().toString();
        Boolean dcheckbox = notifications.isSelected();

        if (!dpass.equals(dcheck_pass)){
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
            if(dpass.equals("")){
                persisted_password = actualpass;
            }else{
                persisted_password = dpass;
            }

            ConfigRequest r;
            r = new ConfigRequest(dname, dlast1, dlast2, ddate, doldpass, persisted_password, dcheckbox,email, answer);
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
