package gabrielle.freville.go4lunch.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.viewModel.RestaurantDetailsViewModel;
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
    private FloatingActionButton selectedRestaurantButton;

    private ImageView ratingStar1;
    private ImageView ratingStar2;
    private ImageView ratingStar3;

    private RestaurantDetailsViewModel restaurantDetailsViewModel;
    private RestaurantViewModel restaurantViewModel;
    private UserViewModel userViewModel;

    private LiveData<Restaurant> restaurantLiveData;

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

        ratingStar1 = findViewById(R.id.activity_details_first_star);
        ratingStar2 = findViewById(R.id.activity_details_second_star);
        ratingStar3 = findViewById(R.id.activity_details_third_star);

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