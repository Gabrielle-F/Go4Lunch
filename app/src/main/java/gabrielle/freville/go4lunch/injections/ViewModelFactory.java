package gabrielle.freville.go4lunch.injections;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public ViewModelFactory(RestaurantRepository restaurantDataProvider, UserRepository userDataProvider) {
        this.restaurantRepository = restaurantDataProvider;
        this.userRepository = userDataProvider;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RestaurantViewModel.class)) {
            return (T) new RestaurantViewModel(restaurantRepository, userRepository);
        }
        throw new IllegalArgumentException("Unknown RestaurantViewModel class");
    }
}
