package com.example.hackatonproject;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> lngs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle arguments = getIntent().getExtras();
        int type = arguments.getInt("type");
        String value = arguments.getString("value");
        ArrayList<Location> list = new ArrayList<>();
        list.add(AppHelper.getInstance().getLocation());
        PointsVisitTask task = new PointsVisitTask(type, null, null, true, list);
        task.parseValueString();
        lngs = new ArrayList<>();
        for (Location location: task.getLocations()) {
            lngs.add(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        for (LatLng latLng: lngs) {
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void closeMap(View view){
        finish();
    }
}
