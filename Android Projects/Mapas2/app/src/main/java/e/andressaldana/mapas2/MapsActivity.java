package e.andressaldana.mapas2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnSatelite = null;
    private Button btnCentrar = null;
    private Button btnAnimar = null;
    private Button btnMover = null;
    private double lat=0, lon=0;
    private Context c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        c = this;

        //Actions
        btnSatelite = (Button) findViewById(R.id.button1);
        btnCentrar = (Button) findViewById(R.id.button2);
        btnAnimar = (Button)findViewById(R.id.button3);
        btnMover = (Button)findViewById(R.id.button4);

        //tipo de mapa
        btnSatelite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (mMap.getMapType() != 2){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                else if (mMap.getMapType() == 2){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        //centrar
        btnCentrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //cdmx
                lat = 19.432608;
                lon = -99.133209;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(	lat, lon), 10));
            }
        });
        //animar loc
        btnAnimar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //paris
                lat = 48.8667;
                lon = 2.33333;
                LatLng coordinate = new LatLng(lat, lon); //Store these lat lng values somewhere. These should be constant.
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
                mMap.animateCamera(location);
            }
        });

        btnMover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                lat +=40;
                lon += 40;
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15);
                mMap.animateCamera(location);
            }
        });
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
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(19.432608, -99.133209))
                .icon(bitmapDescriptorFromVector(this, R.drawable.ic_check_circle_black_24dp))
                .title("Centro Historico"));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                CharSequence msg = "Lat: " + point.latitude + "-" + "Lon: " + point.longitude;
                Toast toast = Toast.makeText(c, msg, Toast.LENGTH_SHORT);
                System.out.println("click");
                toast.show();
            }
        });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
