package example.my_app.co.gettaxix.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import example.my_app.co.gettaxix.R;

public class MainActivity extends AppCompatActivity{





    Button btGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = (Button) findViewById(R.id.getTaxiBtn);

        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.child("temp").child("myFile.abc").putFile(Uri.parse("https://www.google.com/imgres?imgurl=https%3A%2F%2Ffarm8.staticflickr.com%2F7164%2F6528548901_6f043f9077_z.jpg&imgrefurl=https%3A%2F%2Fwww.flickr.com%2Fphotos%2F72065457%40N02%2F6528548901&docid=C0HmAHvR_LdrJM&tbnid=V1tZzucEVJ68DM%3A&vet=10ahUKEwjw6YH30rHhAhXzsnEKHXMVAgYQMwhHKAkwCQ..i&w=500&h=414&bih=722&biw=1536&q=picter&ved=0ahUKEwjw6YH30rHhAhXzsnEKHXMVAgYQMwhHKAkwCQ&iact=mrc&uact=8"));







        btGet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, OrderTaxi.class);
                startActivity(intent);
            }
        });
    }
}