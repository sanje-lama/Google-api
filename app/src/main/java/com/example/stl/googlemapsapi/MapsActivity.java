package com.example.stl.googlemapsapi;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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


        // LatLng is an object.
        LatLng sydney = new LatLng(-34, 151);
        // object mMap
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_new)));
        // Marker option instance.

        LatLng nyc = new LatLng(40.65749, -73.83875);
        mMap.addMarker(new MarkerOptions().position(nyc).title("Marker in NYC").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_here)));
        // Changing the LatLng object we pass in changes the marker( example (nyc)).
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nyc));


        LatLng china = new LatLng(35.000074, 104.999927);
        mMap.addMarker(new MarkerOptions().position(china).title("Marker in China").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_heart)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(china));

        LatLng nepal = new LatLng(28.1083929, 84.0917139);
        mMap.addMarker(new MarkerOptions().position(nepal).title("Marker in Nepal").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_cloud)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nepal));

        LatLng india = new LatLng(22.3511148, 78.6677428);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in India").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_one)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));



        // Adding UiSettings object.
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);


        Geocoder coder = new Geocoder(getApplicationContext());
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName("3105 Astoria Blvd S, Astoria, NY 11102", 5);
            if (address != null) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p1).title("Marker in Astoria - Neptune Diner").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_heart)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



       // This is to check permission.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                double lat = location.getLatitude();
                                double lng = location.getLongitude();
                                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker in NYC"));

                                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker in Kazakhstan").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_stat_names)));



                            }
                        }
                    });
        }



    }

}


