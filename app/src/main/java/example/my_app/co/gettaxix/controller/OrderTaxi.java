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
    EditText passFname, passLname, passPhoneNum, passEmail, passOrigin, passDest;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertaxi);


        submitBtn = (Button) findViewById(R.id.submitBtn);
        passFname = (EditText) findViewById(R.id.fNameTxtBox);
        passLname = (EditText) findViewById(R.id.lNameTxtBox);
        passPhoneNum = (EditText) findViewById(R.id.phoneNumTxtBox);
        passEmail = (EditText) findViewById(R.id.emailTxtBox);
        passOrigin = (EditText) findViewById(R.id.originTxtBox);
        passDest = (EditText) findViewById(R.id.destTxtBox);

        // function the saving passenger information in SharedPreferences
        SharedPreferences();


    }

    public void subBtnClickHandler(View view) {

        Toast.makeText(this, passFname.getText().toString(), Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("idUser",passId.getText().toString());
        
        editor.putString("fName",passFname.getText().toString());
        editor.putString("lName",passLname.getText().toString());
        editor.putString("phone",passPhoneNum.getText().toString());
        editor.putString("email",passEmail.getText().toString());
        editor.commit();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("rides");
        DatabaseReference myRef2 = myRef1.child(passFname.getText().toString());
        DatabaseReference myRef3 = myRef2.child("Last name");
        myRef3.setValue(passLname.getText().toString());
        DatabaseReference myRef4 = myRef2.child("phone");
        myRef4.setValue(passPhoneNum.getText().toString());
        DatabaseReference myRef5 = myRef2.child("Email");
        myRef5.setValue(passEmail.getText().toString());

    }

    public void SharedPreferences() {

        // function the saving passenger information in SharedPreferences
        sp = getSharedPreferences("user",0);
        String fnameUser= sp.getString("fName",null);
        String lnameUser= sp.getString("lName",null);
        String phoneUser= sp.getString("phone",null);
        String emailUser= sp.getString("email",null);

        if( fnameUser != null && lnameUser != null)
        {
            passFname.setText(fnameUser);
            passLname.setText(lnameUser);
            passPhoneNum.setText(phoneUser);
            passEmail.setText(emailUser);

        }

    }
}
