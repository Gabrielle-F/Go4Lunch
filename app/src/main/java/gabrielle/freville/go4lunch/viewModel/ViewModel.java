package gabrielle.freville.go4lunch.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import gabrielle.freville.go4lunch.model.User;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    public LiveData<List<RestaurantResponse>> listLiveData;

    public ViewModel(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void showResult() {
        restaurantRepository.showResult();
    }

    public LiveData<List<RestaurantResponse>> getLiveData() {
        LiveData<List<RestaurantResponse>> liveData = restaurantRepository.listLiveData;
        return liveData;
    }
}
