package gabrielle.freville.go4lunch.injections;

import android.content.Context;

import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class Injection {

    public static RestaurantRepository provideRestaurantData(Context context) {
        return new RestaurantRepository();
    }

    public static UserRepository provideUserData(Context context) {
        return new UserRepository();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        RestaurantRepository restaurantRepository = provideRestaurantData(context);
        UserRepository userRepository = provideUserData(context);
        return new ViewModelFactory(restaurantRepository, userRepository);
    }
}
