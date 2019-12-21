package com.example.alajuelaciudadtecnolgica;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    private static final String route = "https://ciudadtecnologicaalajuela.000webhostapp.com/login.php";
    private Map<String, String> parameters;

    public LoginRequest(String email, String pass, Response.Listener<String> listener){
        super(Request.Method.POST, route, listener, null);
        parameters = new HashMap<>();
        parameters.put("correo",email + "");
        parameters.put("contrasena",pass + "");

    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
