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
    EditText  passName, passPhoneNum, passEmail, passOrigin, passDest,passEmail2;
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
        passDest = (EditText) findViewById(R.id.destTxtBox);
      //  passEmail = (EditText) findViewById(R.id.emailTxtBox); passEmail2

        // function the saving passenger information in SharedPreferences
        SharedPreferences();


    }

    public void subBtnClickHandler(View view){

        Toast.makeText(this, passName.getText().toString(), Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("Name",passName.getText().toString());
        editor.putString("phone",passPhoneNum.getText().toString());
        editor.putString("email",passEmail.getText().toString());
        editor.commit();



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("rides");
        DatabaseReference myRef2 = myRef.child(((EditText) findViewById(R.id.emailTxtBox)).getText().toString());
        DatabaseReference myRef3 = myRef2.child("origin");
        myRef3.setValue(passOrigin.getText().toString());
        DatabaseReference myRef4 = myRef2.child("phone");
        myRef4.setValue(passPhoneNum.getText().toString());
        DatabaseReference myRef6 = myRef2.child("destination");
        myRef6.setValue(passDest.getText().toString());

    }

    public void SharedPreferences()
    {

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
