package gabrielle.freville.go4lunch.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.model.User;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class RestaurantViewModel extends androidx.lifecycle.ViewModel {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    public LiveData<List<Restaurant>> listLiveData;

    public RestaurantViewModel(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void getRestaurants() {
        restaurantRepository.getRestaurantsList();
    }

    public LiveData<List<Restaurant>> getLiveData() {
        listLiveData = restaurantRepository.getRestaurantsLiveData();
        return listLiveData;
    }
}
