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
    private EditText last1;
    private EditText last2;
    private EditText date;
    private EditText email;
    private EditText pass;
    private EditText check_pass;
    private Button btn_register;
    private Button btn_back;
    private CheckBox notifications;
    private Pattern pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.edname);
        last1 = (EditText)findViewById(R.id.edlast1);
        last2 = (EditText)findViewById(R.id.edlast2);
        date = (EditText)findViewById(R.id.eddate);
        email = (EditText)findViewById(R.id.edmail);
        pass = (EditText)findViewById(R.id.edpass);
        check_pass = (EditText)findViewById(R.id.edcheckpass);
        btn_register = (Button)findViewById(R.id.btnacept);
        btn_back = (Button)findViewById(R.id.btnback);
        notifications = (CheckBox)findViewById(R.id.cbnoti);
        pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }


    public void create_user(View v)throws Exception{
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        String dname = name.getText().toString();
        String dlast1 = last1.getText().toString();
        String dlast2 = last2.getText().toString();
        Date ddate = formatter1.parse(date.getText().toString());
        String demail = email.getText().toString();
        String dpass = pass.getText().toString();
        String dcheck_pass = check_pass.getText().toString();
        Boolean dcheckbox = notifications.isSelected();

        Matcher matcher = pattern.matcher(demail);

        if (!dpass.equals(dcheck_pass)){
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
                            Toast.makeText(Register.this,"Usuario creado correctamente",Toast.LENGTH_SHORT);
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
            RegisterRequest r = new RegisterRequest(dname, dlast1, dlast2, ddate, demail, dpass, dcheckbox, answer);
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
