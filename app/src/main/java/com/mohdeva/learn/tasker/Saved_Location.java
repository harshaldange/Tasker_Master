package com.mohdeva.learn.tasker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Saved_Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double saved_lati,saved_longi;
    int taskid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_location);
        Intent current=getIntent();
        taskid=current.getIntExtra("taskid",-1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.saved_location_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        DBController controller=new DBController(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(false);
        }
        else
            mMap.setMyLocationEnabled(true);

        Cursor cursor=controller.getlocationdata(taskid);
        if(cursor.getCount()>=1){
            String[] result=new String[2];
            if(cursor.moveToFirst()) {
                for (int i=0;i<2;i++) {
                    result[i] = (cursor.getString(i));
                }
            }
            saved_longi=Double.parseDouble(result[0]);
            saved_lati=Double.parseDouble(result[1]);
            mMap.addMarker(new MarkerOptions().position(new LatLng(saved_lati,saved_longi)).title(saved_lati+":"+saved_longi));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(saved_lati,saved_longi), 18.0f));
        }
        else
            Toast.makeText(getApplicationContext(),"Error Occured "+cursor.getCount(),Toast.LENGTH_SHORT).show();
    }
    public void onBackPressed()
    {
        Intent back=new Intent(Saved_Location.this,Todo.class);
        startActivity(back);
    }
}
