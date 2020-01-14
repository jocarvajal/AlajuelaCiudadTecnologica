package com.example.alajuelaciudadtecnolgica.StoredPoints;

import android.content.Context;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class Locals {

    public static ItemizedOverlayWithFocus<OverlayItem> getPoints(Context ctx){
        ArrayList<OverlayItem> points = setPoints();
        ItemizedOverlayWithFocus<OverlayItem> localPoints = new ItemizedOverlayWithFocus<OverlayItem>(points,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    //If you want functionality in the points, put it here.
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem item) {
                        return false;
                    }

                    @Override
                    public boolean onItemLongPress(int index, OverlayItem item) {
                        return false;
                    }
                }, ctx);
        localPoints.setFocusItemsOnTap(true);
        return localPoints;
    }

    private static ArrayList<OverlayItem> setPoints(){
        ArrayList<OverlayItem> points = new ArrayList<OverlayItem>();
        points.add(new OverlayItem("Llobet", "Famosa tienda por departamentos en Alajuela",
                new GeoPoint(10.01611,-84.21479)));
        points.add(new OverlayItem("MacDonalds", "Restaurante de comida rapida mundialmente conocido",
                new GeoPoint(10.01601,-84.21347)));
        return points;
    }
}
