package example.my_app.co.gettaxix.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.Equalizer;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import example.my_app.co.gettaxix.R;
import example.my_app.co.gettaxix.model.entities.Passenger;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;


public class OrderTaxi extends AppCompatActivity {

    private FusedLocationProviderClient client;

    Button submitBtn, getLoc, geoCode;
    TextView txtview;
    EditText passName, passPhoneNum, passEmail, passOrigin, passDestination, passEmail2;
    boolean passIsPicked = false;
    SharedPreferences sp;
    Location local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertaxi);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(OrderTaxi.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(OrderTaxi.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    local = location;
                }
            }
        });


        submitBtn = (Button) findViewById(R.id.submitBtn);
        geoCode = (Button) findViewById(R.id.geoCode);
        passName = (EditText) findViewById(R.id.NameTxtBox);
        passPhoneNum = (EditText) findViewById(R.id.phoneNumTxtBox);
        passEmail = (EditText) findViewById(R.id.emailTxtBox);
        passOrigin = (EditText) findViewById(R.id.originTxtBox);
        passDestination = (EditText) findViewById(R.id.destTxtBox);
        passEmail2 = (EditText) findViewById(R.id.emailTxtBox);

        // function the saving passenger information in SharedPreferences
        SharedPreferences();

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void subBtnClickHandler(View view) throws IOException {
        double longitude = 0;
        double latitude = 0;
        Geocoder geocoder = new Geocoder(this);
        List<Address> destAdresses;
        destAdresses = geocoder.getFromLocationName(passDestination.getText().toString(), 1);
        if (destAdresses.size() > 0) {
            latitude = destAdresses.get(0).getLatitude();
            longitude = destAdresses.get(0).getLongitude();
        }

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("Name", passName.getText().toString());
        editor.putString("phone", passPhoneNum.getText().toString());
        editor.putString("email", passEmail.getText().toString());
        editor.commit();


        if(true) {
            Passenger pass = new Passenger(passPhoneNum.getText().toString(), passName.getText().toString(), passEmail.getText().toString(), String.valueOf(local.getLongitude()), String.valueOf(local.getLatitude()), String.valueOf(longitude), String.valueOf(latitude), false);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRides = database.getReference("Rides");
            DatabaseReference myRide = myRides.child(pass.getPhoneNum());
            myRide.setValue(pass);

            Toast.makeText(this, passName.getText().toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, passName.getText().toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, passName.getText().toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, passName.getText().toString(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "phone number is a mandatory", Toast.LENGTH_LONG).show();
        }
    }

    public void geoCodeFunc(View view) throws IOException {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        addresses = geocoder.getFromLocation(local.getLatitude(), local.getLongitude(), 1);
        Address address = addresses.get(0);
        passOrigin.setText(address.getAddressLine(0));
    }

    public void SharedPreferences() {

        // function the saving passenger information in SharedPreferences
        sp = getSharedPreferences("user",0);
        String nameUser= sp.getString("lName",null);
        String phoneUser= sp.getString("phone",null);
        String emailUser= sp.getString("email",null);

        if(  nameUser != null)
        {
            passName.setText(nameUser);
            passPhoneNum.setText(phoneUser);
            passEmail.setText(emailUser);

        }

    }
}

