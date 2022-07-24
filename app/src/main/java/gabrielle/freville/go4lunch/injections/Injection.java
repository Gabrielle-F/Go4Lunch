package gabrielle.freville.go4lunch.injections;

import android.content.Context;

import gabrielle.freville.go4lunch.repositories.RestaurantDetailsRepository;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class Injection {

    public static RestaurantRepository provideRestaurantData(Context context) {
        return new RestaurantRepository();
    }

    public static UserRepository provideUserData(Context context) {
        return new UserRepository();
    }

    public static RestaurantDetailsRepository provideRestaurantDetailsData(Context context) {
        return new RestaurantDetailsRepository();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        RestaurantRepository restaurantRepository = provideRestaurantData(context);
        UserRepository userRepository = provideUserData(context);
        RestaurantDetailsRepository restaurantDetailsRepository = provideRestaurantDetailsData(context);
        return new ViewModelFactory(restaurantRepository, userRepository, restaurantDetailsRepository);
    }
}
