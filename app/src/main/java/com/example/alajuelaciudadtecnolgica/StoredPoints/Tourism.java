package com.example.alajuelaciudadtecnolgica.StoredPoints;

import android.content.Context;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class Tourism {

    public static ItemizedOverlayWithFocus<OverlayItem> getPoints(Context ctx){
        ArrayList<OverlayItem> points = setPoints();
        ItemizedOverlayWithFocus<OverlayItem> tourismPoints = new ItemizedOverlayWithFocus<OverlayItem>(points,
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
        tourismPoints.setFocusItemsOnTap(true);
        return tourismPoints;
    }

    private static ArrayList<OverlayItem> setPoints(){
        ArrayList<OverlayItem> points = new ArrayList<OverlayItem>();
        points.add(new OverlayItem("Parque Juan Santamaria", "Parque el cual su nombre hace tributo al heroe nacional Juan Santamaria",
                new GeoPoint(10.01491,-84.21352)));
        points.add(new OverlayItem("Museo Juan Santamaria", "Museo nacional ubicado en Alajuela",
                new GeoPoint(10.01694,-84.21419)));
        return points;
    }
}
