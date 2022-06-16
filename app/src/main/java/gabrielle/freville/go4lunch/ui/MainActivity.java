package gabrielle.freville.go4lunch.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

import gabrielle.freville.go4lunch.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_toolbar, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }
}