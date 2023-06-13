package com.mvvm.userlivelocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.karumi.dexter.Dexter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ShowingRoutes extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, RoutingListener {
    private GoogleMap mMap;
    Location myLocation = null;
    protected LatLng start = null;
    protected LatLng end = null;
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;
    private List<Polyline> polylines = null;
    EditText userCurrentLocation,userDestinationLocation;
    Button startButton;
    Boolean buttonClickCheck=false;
    LatLng latLngElements=new LatLng(26.8953,75.7485);
    LatLng latLngCityPlace=new LatLng(26.8769,75.7607);
    LatLng latLngPlaza=new LatLng(26.8546,75.7667);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_routes);
        userCurrentLocation=findViewById(R.id.userCurrentLocation);
        userDestinationLocation=findViewById(R.id.userDestinationLocation);
        startButton=findViewById(R.id.buttonStart);
        requestPermision();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        end=new LatLng(26.8593,75.7646);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               buttonClickCheck=true;
            }
        });



    }

    private void requestPermision() {

        if (ContextCompat.checkSelfPermission(ShowingRoutes.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
        } else {
            locationPermission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermission = true;
                    getMyLocation();

                } else {
                    
                }
                return;
            }
        }
    }


    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLocation=location;
                LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                start=latLng;
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                        latLng, 18);
                mMap.animateCamera(cameraUpdate);
                Findroutes(start,end);
                Findroutes(start,latLngElements);
                Findroutes(start,latLngPlaza);
                Findroutes(start,latLngCityPlace);
            }
        });

        //get destination location when user click on map
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//
//                end=latLng;
//
//                mMap.clear();
//
//                start=new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
//                //start route finding
//                Findroutes(start,end);
//            }
//        });


    }
    public void Findroutes(LatLng Start, LatLng End)
    {
        String startAddress=null;
        String endAddress=null;
        if(Start==null || End==null) {
            Toast.makeText(ShowingRoutes.this,"Unable to get location", Toast.LENGTH_LONG).show();
        }
        else
        {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(ShowingRoutes.this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(start.latitude,start.longitude, 1);
                startAddress=addresses.get(0).getAddressLine(0);
                addresses = geocoder.getFromLocation(end.latitude,end.longitude, 1);
                endAddress=addresses.get(0).getAddressLine(0);
            } catch (Exception e) {
                e.printStackTrace();

            }
            userCurrentLocation.setText(startAddress);
            userDestinationLocation.setText(endAddress);
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.WALKING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(Start, End)
                    .key("AIzaSyBivHaHCZbibThFVU4K6Pg3F8gD6XBRfcI")
                    .build();
            routing.execute();
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Toast.makeText(this, "Route Failed", Toast.LENGTH_SHORT).show();
        Log.d("Error123",e.getMessage());
        Findroutes(start,end);
    }

    @Override
    public void onRoutingStart() {
       if(buttonClickCheck)
       {
           mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
               @Override
               public void onMyLocationChange(@NonNull Location location) {
                   Log.d("startBeforeButton",String.valueOf(location.getLongitude()));
                   start=new LatLng(location.getLatitude(),location.getLongitude());
                   Findroutes(start,end);
                   Log.d("startAfterButton",String.valueOf(start.longitude));
               }
           });
       }

    }
    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int shortestRouteIndex) {
   //     mMap.clear();
        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        if(polylines!=null) {
            polylines.clear();
        }
        PolylineOptions polyOptions = new PolylineOptions();
        LatLng polylineStartLatLng=null;
        LatLng polylineEndLatLng=null;
        polylines = new ArrayList<>();
        for (int i = 0; i <arrayList.size(); i++) {

            if(i==shortestRouteIndex)
            {
                polyOptions.color(getResources().getColor(R.color.blueColor));
                polyOptions.width(10);
                polyOptions.geodesic(true);
                polyOptions.addAll(arrayList.get(shortestRouteIndex).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylineStartLatLng=polyline.getPoints().get(0);
                int k=polyline.getPoints().size();
                polylineEndLatLng=polyline.getPoints().get(k-1);
                polylines.add(polyline);

            }
            else {

            }

        }


        MarkerOptions startMarker = new MarkerOptions();
        startMarker.position(polylineStartLatLng);
        startMarker.title("My Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)).anchor(0.5f,0.5f);
        mMap.addMarker(startMarker);

        MarkerOptions endMarker = new MarkerOptions();
        endMarker.position(polylineEndLatLng);
        endMarker.title("Destination");
        mMap.addMarker(endMarker);

//        MarkerOptions elements=new MarkerOptions();
//        LatLng latLngElements=new LatLng(26.8953,75.7485);
//        elements.position(latLngElements);
//        elements.title("Jal Mahal");
//        elements.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//        mMap.addMarker(elements);
//
//
//
//        MarkerOptions plaza=new MarkerOptions();
//        plaza.title("Mansorvar Plaza");
//        LatLng latLngPlaza=new LatLng(26.8546,75.7667);
//        plaza.position(latLngPlaza);
//        plaza.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//        mMap.addMarker(plaza);
//
//        MarkerOptions cityPlace=new MarkerOptions();
//        cityPlace.title("Sunny Trades");
//        LatLng latLngCityPlace=new LatLng(26.8769,75.7607);
//        cityPlace.position(latLngCityPlace);
//        cityPlace.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//        mMap.addMarker(cityPlace);
//




    }

    @Override
    public void onRoutingCancelled() {
        Findroutes(start,end);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Findroutes(start,end);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        getMyLocation();

    }
}