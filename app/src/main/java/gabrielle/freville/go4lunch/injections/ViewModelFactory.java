package gabrielle.freville.go4lunch.injections;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import gabrielle.freville.go4lunch.repositories.RestaurantDetailsRepository;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;
import gabrielle.freville.go4lunch.viewModel.RestaurantDetailsViewModel;
import gabrielle.freville.go4lunch.viewModel.RestaurantViewModel;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantDetailsRepository restaurantDetailsRepository;

    public ViewModelFactory(RestaurantRepository restaurantDataProvider, UserRepository userDataProvider, RestaurantDetailsRepository restaurantDetailsDataProvider) {
        this.restaurantRepository = restaurantDataProvider;
        this.userRepository = userDataProvider;
        this.restaurantDetailsRepository = restaurantDetailsDataProvider;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RestaurantViewModel.class)) {
            return (T) new RestaurantViewModel(restaurantRepository, userRepository);
        } else {
            if (modelClass.isAssignableFrom(UserViewModel.class)) {
                return (T) new UserViewModel(userRepository);
            }
            if (modelClass.isAssignableFrom(RestaurantDetailsViewModel.class)) {
                return (T) new RestaurantDetailsViewModel(restaurantDetailsRepository, restaurantRepository, userRepository);
            }
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
