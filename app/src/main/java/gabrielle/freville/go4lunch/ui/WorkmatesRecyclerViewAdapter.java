package gabrielle.freville.go4lunch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.SelectedRestaurant;
import gabrielle.freville.go4lunch.model.User;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class WorkmatesRecyclerViewAdapter extends RecyclerView.Adapter<WorkmatesRecyclerViewAdapter.WorkmatesViewHolder> {

    private List<User> users;
    private UserViewModel userViewModel;
    private SelectedRestaurant selectedRestaurant;

    WorkmatesRecyclerViewAdapter(List<User> users) {
        this.users = users;
    }

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
        String firstName = user.getFirstName();
        String restaurantChoice = holder.getRestaurantChoice();

        Glide.with(holder.workmateProfilePicture.getContext())
                .load(user.getUrlPicture())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.workmateProfilePicture);
        holder.workmateName.setText(userViewModel.getFirstName(firstName).toString());
        holder.getIfUserEat();
        holder.workmateRestaurantChoice.setText(restaurantChoice);
    }

    public void updateWorkmatesList(final List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public static class WorkmatesViewHolder extends RecyclerView.ViewHolder {

        ImageView workmateProfilePicture;
        TextView workmateRestaurantChoice;
        TextView workmateName;
        TextView workmateIsEating;
        SelectedRestaurant selectedRestaurant;
        UserViewModel userViewModel;

        public WorkmatesViewHolder(@NonNull View itemView) {
            super(itemView);

            workmateProfilePicture.findViewById(R.id.item_workmate_profile_picture);
            workmateName.findViewById(R.id.item_workmate_name);
            workmateIsEating.findViewById(R.id.item_workmate_is_eating);
            workmateRestaurantChoice.findViewById(R.id.item_workmate_choice);
        }

        public void getIfUserEat() {
            Task<DocumentSnapshot> restaurantId = userViewModel.getSelectedRestaurantId();
            if (restaurantId.equals(selectedRestaurant.getRestaurantId())) {
                workmateIsEating.setText(R.string.isEating);
            } else {
                workmateIsEating.setText(R.string.noDecided);
            }
        }

        public String getRestaurantChoice() {
            String restaurantName = null;
            Task<DocumentSnapshot> restaurantId = userViewModel.getSelectedRestaurantId();
            Task<DocumentSnapshot> userId = userViewModel.getUserId();
            if (restaurantId.equals(selectedRestaurant.getRestaurantId()) || userId.equals(selectedRestaurant.getUserId())) {
                restaurantName = userViewModel.getSelectedRestaurantName(selectedRestaurant.getName()).toString();
            } else {
                getIfUserEat();
            }
            return restaurantName;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
