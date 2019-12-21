package com.example.alajuelaciudadtecnolgica;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String route = "https://ciudadtecnologicaalajuela.000webhostapp.com/register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String name, String last1, String last2, Date date, String email, String pass,Boolean notifications, Response.Listener<String> listener){
        super(Request.Method.POST, route, listener, null);
        parameters = new HashMap<>();
        parameters.put("nombre",name + "");
        parameters.put("apellido1",last1 + "");
        parameters.put("apellido2",last2 + "");
        parameters.put("fecha_nacimiento",date + "");
        parameters.put("correo",email + "");
        parameters.put("contrasena",pass + "");

    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
