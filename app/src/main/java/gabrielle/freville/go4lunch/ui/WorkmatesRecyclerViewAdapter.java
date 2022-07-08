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
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;

public class WorkmatesRecyclerViewAdapter extends RecyclerView.Adapter<WorkmatesRecyclerViewAdapter.WorkmatesViewHolder> {

    private List<User> users;
    private RestaurantViewModel restaurantViewModel;

    @NonNull
    @Override
    public WorkmatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_workmate, parent, false);
        return new WorkmatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkmatesViewHolder holder, int position) {
        User user = users.get(position);
    }

    public static class WorkmatesViewHolder extends RecyclerView.ViewHolder {

        ImageView workmateProfilePicture;
        TextView workmateRestaurantChoice;
        TextView workmateName;
        TextView workmateIsEating;

        public WorkmatesViewHolder(@NonNull View itemView) {
            super(itemView);

            workmateProfilePicture.findViewById(R.id.item_workmate_profile_picture);
            workmateName.findViewById(R.id.item_workmate_name);
            workmateIsEating.findViewById(R.id.item_workmate_is_eating);
            workmateRestaurantChoice.findViewById(R.id.item_workmate_choice);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
