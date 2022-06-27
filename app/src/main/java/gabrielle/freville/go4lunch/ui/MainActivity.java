package gabrielle.freville.go4lunch.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.viewModel.ViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RestaurantRepository restaurantRepository;
    private RestaurantRecyclerViewAdapter adapter;
    private ViewModel viewModel;
    private LiveData<List<RestaurantResponse>> listLiveData;
    BottomNavigationView bottomNavigationView;
    private RestaurantsListFragment restaurantsListFragment;
    private MapsFragment mapsFragment;
    private WorkmatesListFragment workmatesListFragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRepository = new RestaurantRepository();
        viewModel = new ViewModel(restaurantRepository);

        mapsFragment = new MapsFragment();
        restaurantsListFragment = new RestaurantsListFragment();
        workmatesListFragment = new WorkmatesListFragment();

        configureToolbar();
        configureBottomBar();
        configureDrawerLayout();
        configureNavigationView();
        returnListRestaurants();
        showResult();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_toolbar, menu);
        return true;
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_main_toolbar);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_view_open, R.string.navigation_view_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showResult() {
        viewModel.showResult();
    }

    public LiveData<List<RestaurantResponse>> returnListRestaurants() {
        listLiveData = viewModel.getLiveData();
        return listLiveData;
    }


}