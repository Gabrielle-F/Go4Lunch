package gabrielle.freville.go4lunch.ui;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;
import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class AuthenticationActivity extends AppCompatActivity {

    private ImageView background;
    private ImageView logo;
    private TextView appPresentation;
    private Button facebookButton;
    private Button googleButton;
    private List<AuthUI.IdpConfig> googleProvider = Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build());
    private UserViewModel userViewModel;
    private View snackBarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        logo = findViewById(R.id.authentication_activity_logo);
        appPresentation = findViewById(R.id.authentication_activity_presentation);
        facebookButton = findViewById(R.id.authentication_activity_facebook_button);
        googleButton = findViewById(R.id.authentication_activity_google_button);

        snackBarContainer = this.findViewById(R.id.authentication_constraint_layout);

        configureUserViewModel();
        configureListeners();
    }

    private void configureUserViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
    }

    private void configureListeners() {
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignInActivity();
            }
        });
    }

    private void startSignInActivity() {
                Intent signInIntent = AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(googleProvider)
                        .setIsSmartLockEnabled(false, true)
                        .build();
                signInLauncher.launch(signInIntent);
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void showSnackBar(String message) {
        Snackbar.make(snackBarContainer, message, Snackbar.LENGTH_SHORT).show();
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            userViewModel.createUser();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            showSnackBar(getString(R.string.connection_succeed));
        } else {
            if (response.getError()!= null) {
                showSnackBar(getString(R.string.error_authentication_canceled));
            } else if (result.getResultCode() == ErrorCodes.NO_NETWORK) {
                showSnackBar(getString(R.string.error_no_internet));
            } else if (result.getResultCode() == ErrorCodes.UNKNOWN_ERROR) {
                showSnackBar(getString(R.string.unknown_error));
            }
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}