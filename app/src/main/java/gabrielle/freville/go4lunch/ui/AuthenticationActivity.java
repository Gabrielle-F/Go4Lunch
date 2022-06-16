package gabrielle.freville.go4lunch.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import gabrielle.freville.go4lunch.R;

public class AuthenticationActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView appPresentation;
    private Button facebookButton;
    private Button googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        logo = findViewById(R.id.authentication_activity_logo);
        appPresentation = findViewById(R.id.authentication_activity_presentation);
        facebookButton = findViewById(R.id.authentication_activity_facebook_button);
        googleButton = findViewById(R.id.authentication_activity_google_button);

        configureListeners();
    }

    private void configureListeners() {
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}