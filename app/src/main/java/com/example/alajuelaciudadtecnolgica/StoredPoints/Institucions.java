package com.example.alajuelaciudadtecnolgica.StoredPoints;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class Institucions {

    public static ArrayList<Marker> getMarkers(MapView mapView){
        ArrayList<Marker> markers = new ArrayList<Marker>();
        markers.add(getMarker(mapView, 10.01704,-84.21199, "Cruz Roja"));
        markers.add(getMarker(mapView, 10.01575,-84.21391, "UTN Alajuela"));
        return markers;
    }

    private static Marker getMarker(MapView mapView, double latitude, double length, String title){
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(latitude, length));
        marker.setTitle(title);
        return marker;
    }
}
