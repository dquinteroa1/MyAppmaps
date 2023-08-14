package com.example.myappmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
 GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //referencia del mapa
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        //personalizar el mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 = CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0127046603246574, -79.46941689110616), 17);
        mMap.moveCamera(camUpd1);
        LatLng madrid = new LatLng(-1.0125788002149538, -79.46950960461741);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(17)
                .bearing(20) //noreste arriba
                .tilt(30) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(camUpd3);
        mMap.setOnMapClickListener(this);
        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.0125922091735886, -79.46805852951518))
                .add(new LatLng(-1.0123723023782123, -79.47033304267104))
                .add(new LatLng(-1.013369928208309, -79.47013455921169))
                .add(new LatLng(-1.0134074732604292, -79.46829456389926))
                .add(new LatLng(-1.0125922091735886, -79.46805852951518));
        lineas.width(8);
        lineas.color(Color.RED);
        mMap.addPolyline(lineas);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng)
                .title("Punto"));

    }
}