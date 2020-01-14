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

    public RegisterRequest(String name, String last_name_1, String last_name_2, Date birthday, String email, String password,Boolean notifications, Response.Listener<String> listener){
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
        parameters.put("correo",email + "");
        parameters.put("contrasena",password + "");
        parameters.put("notificaciones",not + "");

    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
