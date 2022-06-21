package gabrielle.freville.go4lunch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.NearbyListResponse;
import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.utils.RestaurantService;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RestaurantService restaurantService;
    private List<Restaurant> restaurants;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurantName;
        TextView restaurantTypeAddress;
        TextView restaurantOpeningHours;
        TextView restaurantDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName.findViewById(R.id.item_restaurant_name);
            restaurantTypeAddress.findViewById(R.id.item_restaurant_address);
            restaurantOpeningHours.findViewById(R.id.item_restaurant_open);
            restaurantDistance.findViewById(R.id.item_restaurant_distance);
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
