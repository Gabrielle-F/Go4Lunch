package gabrielle.freville.go4lunch.viewModel;

import androidx.lifecycle.LiveData;

import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.repositories.RestaurantDetailsRepository;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class RestaurantDetailsViewModel extends androidx.lifecycle.ViewModel {

    private RestaurantDetailsRepository restaurantDetailsRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    public LiveData<Restaurant> restaurantDetailsLiveData;
    public String placeId;

    public RestaurantDetailsViewModel(RestaurantDetailsRepository restaurantDetailsRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantDetailsRepository = restaurantDetailsRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public LiveData<Restaurant> getRestaurantDetails() {
        restaurantDetailsLiveData = restaurantDetailsRepository.getRestaurantDetails();
        return restaurantDetailsLiveData;
    }

    public LiveData<Restaurant> getRestaurantId(String id) {
        return restaurantDetailsRepository.getRestaurantId(id);
    }
}
