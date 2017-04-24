package com.example.mahri.loginestudo;

/**
 * Created by mahri on 09/04/2017.
 * Fragment com o MAPA
 */

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //marcar uma posição no mapa, buscar minha localizaçao etc...
        // Add a marker in Sydney and move the camera
        LatLng ponto1 = new LatLng(-23.085296, -47.201933);
        mMap.addMarker(new MarkerOptions().position(ponto1).title("Ponto em Indaiatuba"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ponto1));
        LatLng ponto2 = new LatLng(-23.087235, -47.204536);
        mMap.addMarker(new MarkerOptions().position(ponto2).title("Ponto em Indaiatuba"));

        LatLng ponto3 = new LatLng(-23.095666, -47.304552);
        mMap.addMarker(new MarkerOptions().position(ponto3).title("Ponto em Indaiatuba"));

        LatLng ponto4 = new LatLng(-23.015666, -47.934552);
        mMap.addMarker(new MarkerOptions().position(ponto4).title("Ponto em Indaiatuba"));

        LatLng ponto5 = new LatLng(-23.195666, -47.104552);
        mMap.addMarker(new MarkerOptions().position(ponto5).title("Ponto em Indaiatuba"));

        LatLng ponto6 = new LatLng(-23.995666, -47.164552);
        mMap.addMarker(new MarkerOptions().position(ponto6).title("Ponto em Indaiatuba"));



/*
        LatLng MELBOURNE = new LatLng(-23.025666, -47.304552);
    mMap.addMarker(new MarkerOptions()
    .position(MELBOURNE)
    .title("Melbourne")
    .snippet("Population: 4,137,400")
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pause_dark))); */
}
}