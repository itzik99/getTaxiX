package example.my_app.co.gettaxix.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.provider.FirebaseInitProvider;

import java.security.PublicKey;

import example.my_app.co.gettaxix.R;
import example.my_app.co.gettaxix.model.entities.Passenger;

public class OrderTaxi extends AppCompatActivity {

    Button submitBtn;
    EditText  passName, passPhoneNum, passEmail, passOrigin, passDestination, passEmail2;
    boolean passIsPicked = false;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertaxi);


        submitBtn = (Button) findViewById(R.id.submitBtn);
        passName = (EditText) findViewById(R.id.NameTxtBox);
        passPhoneNum = (EditText) findViewById(R.id.phoneNumTxtBox);
        passEmail = (EditText) findViewById(R.id.emailTxtBox);
        passOrigin = (EditText) findViewById(R.id.originTxtBox);
        passDestination = (EditText) findViewById(R.id.destTxtBox);
        passEmail2 = (EditText) findViewById(R.id.emailTxtBox);

        // function the saving passenger information in SharedPreferences
        SharedPreferences();
        
    }

    public void subBtnClickHandler(View view) {


        SharedPreferences.Editor editor=sp.edit();
        editor.putString("Name",passName.getText().toString());
        editor.putString("phone",passPhoneNum.getText().toString());
        editor.putString("email",passEmail.getText().toString());
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
