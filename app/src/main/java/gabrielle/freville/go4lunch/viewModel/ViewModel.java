package gabrielle.freville.go4lunch.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private RestaurantRepository restaurantRepository;
    public LiveData<List<RestaurantResponse>> listLiveData;

    public ViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void showResult() {
        restaurantRepository.showResult();
    }

    public LiveData<List<RestaurantResponse>> getLiveData() {
        LiveData<List<RestaurantResponse>> liveData = restaurantRepository.listLiveData;
        return liveData;
    }
}
