package gabrielle.freville.go4lunch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.viewModel.ViewModel;

public class RestaurantsListFragment extends Fragment {

    private GoogleMap googleMap;
    private ViewModel viewModel;
    private String name;
    private String location;
    private String type;
    private int rating;
    private int userRatingsTotal;
    private RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        this.getLatLng();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        recyclerView.findViewById(R.id.list_restaurant_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void getUserLocation() {
        googleMap.getCameraPosition();
    }

    private void getLatLng() {
        LatLng latLng = new com.google.android.gms.maps.model.LatLng(48.69141, 1.07869);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }


}
