package com.example.alajuelaciudadtecnolgica;

import android.content.Context;

import com.example.alajuelaciudadtecnolgica.StoredPoints.BusStops;
import com.example.alajuelaciudadtecnolgica.StoredPoints.Institucions;
import com.example.alajuelaciudadtecnolgica.StoredPoints.Locals;
import com.example.alajuelaciudadtecnolgica.StoredPoints.Tourism;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class RequestPointsData {

    MapView mapView;
    Context ctx;

    public RequestPointsData(MapView mapView, Context ctx){
        this.mapView = mapView;
        this.ctx = ctx;
    }

    public ArrayList<Marker> getBusStops(){
        return BusStops.getMarkers(mapView);
    }

    public ArrayList<Marker> getInstitutions(){
        return Institucions.getMarkers(mapView);
    }

    public ItemizedOverlayWithFocus<OverlayItem> getLocals(){
        return Locals.getPoints(ctx);
    }

    public ItemizedOverlayWithFocus<OverlayItem> getTourism(){
        return Tourism.getPoints(ctx);
    }
}
