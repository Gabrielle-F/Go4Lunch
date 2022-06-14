package gabrielle.freville.go4lunch.repositories;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.utils.RestaurantService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository implements ObservableSource {

    private static final String COLLECTION_NAME = "restaurants";
    private Disposable disposable;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private RestaurantService restaurantService;

    private CollectionReference getRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public DisposableObserver<List<RestaurantResponse>> getSubscriber(String name, String location, String type, int rating, int userRatingsTotal) {
        return new DisposableObserver<List<RestaurantResponse>>() {
            @Override
            public void onNext(@NonNull List<RestaurantResponse> restaurantResponses) {
                RestaurantService restaurantService = RestaurantService.retrofit.create(RestaurantService.class);
                Call<List<RestaurantResponse>> call = restaurantService.getNearbyRestaurantsList(name, location, type, rating, userRatingsTotal);
                call.enqueue(new Callback<List<RestaurantResponse>>() {
                    @Override
                    public void onResponse(Call<List<RestaurantResponse>> call, Response<List<RestaurantResponse>> response) {
                        if (call != null) {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<RestaurantResponse>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        };
    }

    @Override
    public void subscribe(@NonNull Observer observer) {

    }
}
