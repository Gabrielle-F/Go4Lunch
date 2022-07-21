package gabrielle.freville.go4lunch.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private ImageView restaurantPhoto;
    private TextView restaurantName;
    private TextView restaurantAddress;
    private ImageButton callRestaurant;
    private ImageButton likeRestaurant;
    private ImageButton websiteRestaurant;
    private TextView callButtonText;
    private TextView likeButtonText;
    private TextView websiteButtonText;

    private RestaurantViewModel restaurantViewModel;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        restaurantPhoto = findViewById(R.id.activity_details_photo);
        restaurantName = findViewById(R.id.activity_details_name);
        restaurantAddress = findViewById(R.id.activity_details_address);
        callRestaurant = findViewById(R.id.activity_restaurant_details_phone_button);
        likeRestaurant = findViewById(R.id.activity_restaurant_details_like_button);
        websiteRestaurant = findViewById(R.id.activity_restaurant_details_website_button);
        callButtonText = findViewById(R.id.activity_restaurant_details_call);
        likeButtonText = findViewById(R.id.activity_restaurant_details_like);
        websiteButtonText = findViewById(R.id.activity_details_website);

        configureRestaurantViewModel();
        configureUserViewModel();
    }

    private void configureRestaurantViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        this.restaurantViewModel = new ViewModelProvider(this, factory).get(RestaurantViewModel.class);
    }

    private void configureUserViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(this);
        this.userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);
    }

}