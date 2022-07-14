package gabrielle.freville.go4lunch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.Restaurant;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private List<Restaurant> restaurantsList;

    public RestaurantRecyclerViewAdapter(List<Restaurant> items){
        restaurants = items;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        holder.restaurantName.setText(restaurant.getName());
        holder.restaurantAddress.setText(restaurant.getAddress());
        holder.restaurantOpeningHours.setText(restaurant.getHours());
        holder.restaurantDistance.setText(restaurant.getDistance());
    }

    public void updateRestaurantsList(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView restaurantName;
        TextView restaurantAddress;
        TextView restaurantOpeningHours;
        TextView restaurantDistance;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName.findViewById(R.id.item_restaurant_name);
            restaurantAddress.findViewById(R.id.item_restaurant_address);
            restaurantOpeningHours.findViewById(R.id.item_restaurant_open);
            restaurantDistance.findViewById(R.id.item_restaurant_distance);
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
