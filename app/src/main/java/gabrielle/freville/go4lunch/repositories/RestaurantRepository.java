package gabrielle.freville.go4lunch.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.utils.RestaurantService;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RestaurantRepository {

    private static final String COLLECTION_NAME = "restaurants";
    private RestaurantService restaurantService;
    private static final String TAG = "ListRestaurants";

    private String name;
    private String geometry;
    private String type = "restaurant";
    private Boolean opennow = true;
    private String openingHours;
    private String vicinity;
    private int rating;
    private int userRatingsTotal;
    private String photoReference;

    public MutableLiveData<List<Restaurant>> listLiveData = new MutableLiveData<>();

    private CollectionReference getRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    private RestaurantService getRestaurantService() {
        restaurantService = getRetrofitInstance().create(RestaurantService.class);
        return restaurantService;
    }

    public void getRestaurantsList() {
        this.getRestaurantService();
        this.getRetrofitInstance();
        restaurantService.getRestaurants(name, type, geometry, opennow, openingHours, vicinity, photoReference, rating, userRatingsTotal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<RestaurantResponse>, List<Restaurant>>() {
                    @Override
                    public List<Restaurant> apply(List<RestaurantResponse> restaurantResponses) throws Exception {
                        List<Restaurant> restaurantList = new ArrayList<>();
                        for (RestaurantResponse restaurant : restaurantResponses) {
                            Restaurant restaurantInstance = new Restaurant(
                                    name = restaurant.getName(),
                                    vicinity = restaurant.getAddress(),
                                    opennow = restaurant.getOpennow(),
                                    rating = restaurant.getRating(),
                                    userRatingsTotal = restaurant.getUserRatingsTotal(),
                                    photoReference = restaurant.getPhotoReference()
                            );
                            restaurantList.add(restaurantInstance);
                        }
                        return restaurantList;
                    }
                })
                .subscribe(new Observer<List<Restaurant>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<Restaurant> restaurants) {
                        listLiveData.setValue(restaurants);
                        Log.e(TAG, "Return restaurant list");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Restaurant>> getRestaurantsLiveData() {
        return listLiveData;
    }

    public Retrofit getRetrofitInstance() {
        Retrofit retrofitInstance = restaurantService.retrofit;
        return retrofitInstance;
    }
}
