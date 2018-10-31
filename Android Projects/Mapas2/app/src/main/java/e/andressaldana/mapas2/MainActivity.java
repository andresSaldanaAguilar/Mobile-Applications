package e.andressaldana.mapas2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Button btnSatelite = null;
    static final LatLng Durban = new LatLng(-29.858680, 31.021840);
    private GoogleMap googleMap;
    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (googleMap == null) {
                mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
                mapFragment.getMapAsync(this);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        btnSatelite = (Button) findViewById(R.id.button1);
        btnSatelite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                /*if (googleMap.isSatellite())
                    mapa.setSatellite(false);
                else
                    mapa.setSatellite(true);*/

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        final Marker marker_Durban = googleMap.addMarker(new MarkerOptions()
                .position(Durban)
                .snippet("Durban, KwaZulu-Natal, South Africa")
                .title("Durban"));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {


            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });

    }
}
