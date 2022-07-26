package gabrielle.freville.go4lunch.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;

public class RestaurantsListFragment extends Fragment {

    private RestaurantViewModel restaurantViewModel;
    private RecyclerView recyclerView;
    private RestaurantRecyclerViewAdapter adapter;
    private List<Restaurant> restaurantsList = new ArrayList<>();
    private LiveData<List<Restaurant>> listLiveData;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 100;
    private Location currentLocation;
    private @NonNull int[] grantResults;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        recyclerView = view.findViewById(R.id.list_restaurant_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RestaurantRecyclerViewAdapter(restaurantsList);
        recyclerView.setAdapter(adapter);

        configureFusedLocationProviderClient();
        getLocation();

        configureViewModel();
        listLiveData = restaurantViewModel.getLiveData();
        Objects.requireNonNull(listLiveData).observe(getViewLifecycleOwner(), this::initList);

        return view;
    }

    private void configureViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(getContext());
        this.restaurantViewModel = new ViewModelProvider(this, factory).get(RestaurantViewModel.class);
    }

    public void initList(List<Restaurant> restaurants) {
        adapter.updateRestaurantsList(restaurants);
    }

    @Override
    public void onStart() {
        restaurantViewModel.getRestaurants();
        super.onStart();
    }

    private void configureFusedLocationProviderClient() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission
                (getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission
                        (getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                }
            }
        });
    }

    private void requestPermissionsResult(int requestCode) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
                break;
        }
    }
}
