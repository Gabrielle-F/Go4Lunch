package gabrielle.freville.go4lunch.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RestaurantRepository restaurantRepository;
    private RestaurantViewModel restaurantViewModel;
    BottomNavigationView bottomNavigationView;
    private RestaurantsListFragment restaurantsListFragment;
    private MapsFragment mapsFragment;
    private WorkmatesListFragment workmatesListFragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private NavigationBarView.OnItemSelectedListener selectedListener;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRepository = new RestaurantRepository();

        mapsFragment = new MapsFragment();
        restaurantsListFragment = new RestaurantsListFragment();
        workmatesListFragment = new WorkmatesListFragment();

        configureToolbar();
        configureItemSelectedListener();
        configureBottomBar();
        configureDrawerLayout();
        configureNavigationView();

        configureRestaurantViewModel();
        configureUserViewModel();
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
        bottomNavigationView.setOnItemSelectedListener(selectedListener);
        bottomNavigationView.setSelectedItemId(R.id.menu_bottom_bar_map_view);
    }

    private void configureItemSelectedListener() {
        selectedListener = item -> {
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
            return false;
        };
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.white));
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
        navigationView.setBackgroundColor(getResources().getColor(R.color.toolbar));
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