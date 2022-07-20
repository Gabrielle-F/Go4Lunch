package gabrielle.freville.go4lunch.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.messaging.RemoteMessage;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.utils.NotificationsService;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView notificationsTextView;
    private ImageButton notificationsButton;
    private UserViewModel userViewModel;
    private NotificationsService notificationsService;
    private RemoteMessage.Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        configureToolbar();
        configureUserViewModel();
        configureListeners();
    }

    private void configureUserViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        this.userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);
    }

    private void configureListeners() {
        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userViewModel.getNotifications() != null) {
                    notificationsButton.setActivated(true);
                    notificationsService.sendVisualNotification(notification);
                } else {
                    notificationsButton.setActivated(false);
                }
            }
        });
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_settings_toolbar);
    }
}