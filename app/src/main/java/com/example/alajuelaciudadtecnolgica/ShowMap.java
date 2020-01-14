package com.example.alajuelaciudadtecnolgica;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.os.Build;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import  androidx.core.content.res.ResourcesCompat;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import java.util.ArrayList;
import java.util.Map;

import android.preference.PreferenceManager;

import com.example.alajuelaciudadtecnolgica.StoredPoints.BusStops;

import org.osmdroid.config.Configuration;

public class ShowMap extends AppCompatActivity {

    ArrayList<OverlayItem> puntos = new ArrayList<>();
    private MapView myOpenMapView;
    private MapController myMapController;
    private GeoPoint posicionActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        String option = getIntent().getStringExtra("Type");
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        if (tengoPermisoEscritura()){
            GeoPoint alajuela = new GeoPoint(10.0164, -84.2138);

            myOpenMapView = (MapView) findViewById(R.id.openmapview);
            myOpenMapView.setTileSource(TileSourceFactory.MAPNIK);
            myOpenMapView.setBuiltInZoomControls(true);
            myOpenMapView.setMultiTouchControls(true);

            myMapController = (MapController)myOpenMapView.getController();
            myMapController.setCenter(alajuela);
            myMapController.setZoom(17);

            RequestPointsData requestPointsData = new RequestPointsData(myOpenMapView, ctx);

            if (option.equals("BusStops")){
                ArrayList<Marker> busMarkers = requestPointsData.getBusStops();
                setMarkers(busMarkers);
            }else if (option.equals("Institutions")){
                ArrayList<Marker> institutionsMarkers = requestPointsData.getInstitutions();
                setMarkers(institutionsMarkers);
            }else if (option.equals("Tourism")){
                ItemizedOverlayWithFocus<OverlayItem> tourism = requestPointsData.getTourism();
                myOpenMapView.getOverlays().add(tourism);
            }else if (option.equals("Local")){
                ItemizedOverlayWithFocus<OverlayItem> locals = requestPointsData.getLocals();
                myOpenMapView.getOverlays().add(locals);
            }else if (option.equals("All")){
                setMarkers(requestPointsData.getBusStops());
                setMarkers(requestPointsData.getInstitutions());
                myOpenMapView.getOverlays().add(requestPointsData.getLocals());
                myOpenMapView.getOverlays().add(requestPointsData.getTourism());
            }
        }
    }

    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        myOpenMapView.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        myOpenMapView.onPause();  //needed for compass, my location overlays, v6.0.0 and up
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

    public boolean tengoPermisoUbicacion() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                return false;
            }
        } else {
            return true;
        }
    }

    public void actualizaPosicionActual(Location location) {
        posicionActual = new GeoPoint(location.getLatitude(), location.getLongitude());
        myMapController.setCenter(posicionActual);
        if (puntos.size() > 1)
            puntos.remove(1);
        OverlayItem marcador = new OverlayItem("Estás aquí", "Posicion actual", posicionActual);
        marcador.setMarker(ResourcesCompat.getDrawable(getResources(), R.drawable.center, null));
        puntos.add(marcador);
        refrescaPuntos();
    }

    private void refrescaPuntos() {
        myOpenMapView.getOverlays().clear();
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);
    }

    private void setMarkers(ArrayList<Marker> markers){
        int arraySize = markers.size();

        for (int i = 0; i < arraySize; i++){
            myOpenMapView.getOverlays().add(markers.get(i));
        }
    }
}
