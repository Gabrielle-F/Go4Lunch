package gabrielle.freville.go4lunch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Objects;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;

public class RestaurantsListFragment extends Fragment {

    private GoogleMap googleMap;
    private RestaurantViewModel restaurantViewModel;
    private RecyclerView recyclerView;
    private RestaurantRecyclerViewAdapter adapter;
    private List<Restaurant> restaurantsList = null;
    private MainActivity mainActivity;
    private LiveData<List<Restaurant>> listLiveData;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.getLatLng();

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

        configureViewModel();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void getLatLng() {
        LatLng latLng = new com.google.android.gms.maps.model.LatLng(48.69141, 1.07869);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void configureViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(getContext());
        this.restaurantViewModel = new ViewModelProvider(this, factory).get(RestaurantViewModel.class);
    }

    @Override
    public void onStart() {
        restaurantViewModel.getRestaurants();
        super.onStart();
    }

    public LiveData<List<Restaurant>> returnRestaurantsList() {
        listLiveData = restaurantViewModel.getLiveData();
        return listLiveData;
    }
}
