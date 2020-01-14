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

    public ConfigRequest(String name, String last_name_1, String last_name_2, Date birthday,String old_password, String password,Boolean notifications,String email, Response.Listener<String> listener){
        super(Method.POST, route, listener, null);
        int not;
        if(notifications){
            not = 1;
        }else{
            not = 0;
        }
        parameters = new HashMap<>();
        parameters.put("nombre",name + "");
        parameters.put("apellido1",last_name_1 + "");
        parameters.put("apellido2",last_name_2 + "");
        parameters.put("fecha_nacimiento",birthday + "");
        parameters.put("contrasena_vieja",old_password + "");
        parameters.put("contrasena",password + "");
        parameters.put("notificaciones",not + "");
        parameters.put("correo",email + "");

    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
