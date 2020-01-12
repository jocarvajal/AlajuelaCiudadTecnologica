package com.example.alajuelaciudadtecnolgica;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ConfigRequest extends StringRequest{
    private static final String route = "https://ciudadtecnologicaalajuela.000webhostapp.com/configuration.php";
    private Map<String, String> parameters;

    public ConfigRequest(String name, String last1, String last2, Date date,String oldpass, String pass,Boolean notifications,String email, Response.Listener<String> listener){
        super(Method.POST, route, listener, null);
        int not;
        if(notifications){
            not = 1;
        }else{
            not = 0;
        }
        parameters = new HashMap<>();
        parameters.put("nombre",name + "");
        parameters.put("apellido1",last1 + "");
        parameters.put("apellido2",last2 + "");
        parameters.put("fecha_nacimiento",date + "");
        parameters.put("contrasena_vieja",oldpass + "");
        parameters.put("contrasena",pass + "");
        parameters.put("notificaciones",not + "");
        parameters.put("correo",email + "");

    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
