package gabrielle.freville.go4lunch.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.viewModel.ViewModel;

public class MainActivity extends AppCompatActivity {

    private RestaurantRepository restaurantRepository;
    private RestaurantRecyclerViewAdapter adapter;
    private ViewModel viewModel;
    private LiveData<List<RestaurantResponse>> listLiveData;
    BottomNavigationView bottomNavigationView;
    private RestaurantsListFragment restaurantsListFragment;
    private MapsFragment mapsFragment;
    private WorkmatesListFragment workmatesListFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRepository = new RestaurantRepository();
        viewModel = new ViewModel(restaurantRepository);
        configureToolbar();
        configureBottomBar();
        showResult();
        returnListRestaurants();
        mapsFragment = new MapsFragment();
        restaurantsListFragment = new RestaurantsListFragment();
        workmatesListFragment = new WorkmatesListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_toolbar, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureBottomBar() {
        bottomNavigationView = findViewById(R.id.activity_main_bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.menu_bottom_bar_map_view);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_bottom_bar_map_view:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, mapsFragment).commit();
                return true;
            case R.id.menu_bottom_bar_restaurant_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, restaurantsListFragment).commit();
                return true;
            case R.id.menu_bottom_bar_workmates:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, workmatesListFragment).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showResult() {
        viewModel.showResult();
    }

    public LiveData<List<RestaurantResponse>> returnListRestaurants() {
        listLiveData = viewModel.getLiveData();
        return listLiveData;
    }
}