package gabrielle.freville.go4lunch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.User;

public class RestaurantDetailsRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantDetailsRecyclerViewAdapter.RestaurantDetailsViewHolder> {

    private List<User> users;

    @NonNull
    @Override
    public RestaurantDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workmate_restaurant_details_list, parent, false);
        return new RestaurantDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailsViewHolder holder, int position) {
        User user = users.get(position);


        holder.username.setText(user.getFirstName());
    }

    public static class RestaurantDetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView userProfilPicture;
        TextView username;
        TextView userIsJoining;

        public RestaurantDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            userProfilPicture.findViewById(R.id.activity_restaurant_details_user_profile_picture);
            username.findViewById(R.id.activity_restaurant_details_user_name);
            userIsJoining.findViewById(R.id.activity_restaurant_details_user_is_joining);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
