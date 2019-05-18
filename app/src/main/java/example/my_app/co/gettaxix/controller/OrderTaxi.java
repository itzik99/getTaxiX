package example.my_app.co.gettaxix.controller;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.Equalizer;
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
import example.my_app.co.gettaxix.R;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;


public class OrderTaxi extends AppCompatActivity {

    private FusedLocationProviderClient client;

    LocationListener locationListener;
    LocationManager locationManager;
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


        txtview = (TextView) findViewById(R.id.locTxtView);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        getLoc = (Button) findViewById(R.id.getLoc);
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

    public void subBtnClickHandler(View view) {

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("Name", passName.getText().toString());
        editor.putString("phone", passPhoneNum.getText().toString());
        editor.putString("email", passEmail.getText().toString());
        editor.commit();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRides = database.getReference("rides");
        DatabaseReference myRide = myRides.child(passPhoneNum.getText().toString());

        DatabaseReference rideName = myRide.child("name");
        rideName.setValue(passName.getText().toString());
        DatabaseReference ridePhone = myRide.child("phone");
        ridePhone.setValue(passPhoneNum.getText().toString());
        DatabaseReference rideEmail = myRide.child("email");
        rideEmail.setValue(passEmail.getText().toString());
        DatabaseReference rideOrig = myRide.child("origin");
        rideOrig.setValue(passOrigin.getText().toString());
        DatabaseReference rideDest = myRide.child("destination");
        rideDest.setValue(passDestination.getText().toString());
        DatabaseReference isPicked = myRide.child("isPicked");
        isPicked.setValue(passIsPicked);

        Toast.makeText(this, passName.getText().toString(), Toast.LENGTH_LONG).show();

    }

    public void getLocFunc(View view) {
        Toast.makeText(this, "getloc!", Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                txtview.append("\n"+location.getLatitude()+ " "+location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates("gps", 3000, 15, locationListener);

    }

    public void geoCodeFunc(View view) {
        Toast.makeText(this, "geocode!", Toast.LENGTH_SHORT).show();
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

