package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends AppCompatActivity {

    private EditText name;
    private EditText last_name_1;
    private EditText last_name_2;
    private EditText birthday;
    private EditText email;
    private EditText password;
    private EditText check_password;
    private Button btn_register;
    private Button btn_back;
    private CheckBox notifications;
    private Pattern pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.edname);
        last_name_1 = (EditText)findViewById(R.id.edlast1);
        last_name_2 = (EditText)findViewById(R.id.edlast2);
        birthday = (EditText)findViewById(R.id.eddate);
        email = (EditText)findViewById(R.id.edmail);
        password = (EditText)findViewById(R.id.edpass);
        check_password = (EditText)findViewById(R.id.edcheckpass);
        btn_register = (Button)findViewById(R.id.btnacept);
        btn_back = (Button)findViewById(R.id.btnback);
        notifications = (CheckBox)findViewById(R.id.cbnoti);
        pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }


    public void create_user(View v)throws Exception{
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        String name_database = name.getText().toString();
        String last_name_1_database = last_name_1.getText().toString();
        String last_name_2_database = last_name_2.getText().toString();
        Date birthday_database = formatter1.parse(birthday.getText().toString());
        String email_database = email.getText().toString();
        String password_database = password.getText().toString();
        String check_password_database = check_password.getText().toString();
        Boolean checkbox_database = notifications.isSelected();

        Matcher matcher = pattern.matcher(email_database);

        if (!password_database.equals(check_password_database)){
            Toast.makeText(Register.this, "No coinciden contraseñas", Toast.LENGTH_SHORT).show();
        }else if(!matcher.find()){
            Toast.makeText(Register.this, "Formato de correo incorrecto", Toast.LENGTH_SHORT).show();
        }else{
            Response.Listener<String> answer = new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    try{
                        JSONObject jsonAnswer = new JSONObject(response);
                        boolean correct = jsonAnswer.getBoolean("success");
                        if (correct){
                            Toast.makeText(Register.this,"Usuario creado correctamente",Toast.LENGTH_SHORT).show();
                            Intent c = new Intent(Register.this, Login.class);
                            Register.this.startActivity(c);
                            Register.this.finish();
                        }else{
                            AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
                            alert.setMessage("Fallo en la creación de cuenta").setNegativeButton("Reintentar", null)
                                    .create()
                                    .show();
                        }
                    }catch(JSONException e){
                        e.getMessage();
                    }
                }
            };
            RegisterRequest r = new RegisterRequest(name_database, last_name_1_database, last_name_2_database, birthday_database, email_database, password_database, checkbox_database, answer);
            RequestQueue queue = Volley.newRequestQueue(Register.this);
            queue.add(r);
        }

    }

    public void returning(View v){
        Intent i = new Intent(Register.this, Login.class);
        Register.this.startActivity(i);
        Register.this.finish();
    }
}
