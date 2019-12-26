package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Build;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;


public class ShowMap extends AppCompatActivity {

    private MapView myOpenMapView;
    private MapController myMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        if (tengoPermisoEscritura()){
            GeoPoint alajuela = new GeoPoint(10.0162497, -84.2116318);
            myOpenMapView = (MapView)findViewById(R.id.openmapview);
            myOpenMapView.setBuiltInZoomControls(true);
            myMapController = (MapController)myOpenMapView.getController();
            myMapController.setCenter(alajuela);
            myMapController.setZoom(6);

            myOpenMapView.setMultiTouchControls(true);
        }


    }

    public void returning(View v){
        Intent i = new Intent(ShowMap.this, Menu.class);
        ShowMap.this.startActivity(i);
        ShowMap.this.finish();
    }

    public boolean tengoPermisoEscritura() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }
}
