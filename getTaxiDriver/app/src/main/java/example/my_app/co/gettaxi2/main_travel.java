package example.my_app.co.gettaxi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import example.my_app.co.gettaxi2.R;

public class main_travel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_travel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("MY Toolbar !");
        //toolbar.setSubtitle("This is my smart toolbar !");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String title = (String) item.getTitle();
                Toast.makeText(main_travel.this, title + " Selected !", Toast.LENGTH_SHORT).show();

                switch (item.getItemId()) {

                    case R.id.save:// show simple snack bar
                        // Perform the individual Menu Actions.
                        break;

                    case R.id.delete: // show snack bar with action call back
                        // Perform some Actions.

                        break;
                    case R.id.travelEnded://show snack bar with color text
                        break;
                }
                return true;
            }
        });
    }
}
