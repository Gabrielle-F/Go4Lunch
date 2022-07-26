package gabrielle.freville.go4lunch.repositories;

import static android.location.LocationManager.*;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.go4lunch.R;
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
    private Restaurant restaurant = new Restaurant();

    private String name;
    private Boolean opennow = true;
    private String openingHours;
    private String vicinity;
    private int rating;
    private int userRatingsTotal;
    private String photoReference;

    private String type = "restaurant";
    private String location = "48.691335913411834%2C1.0786886399375246";
    private int radius = 5000;
    private int key = R.string.google_maps_key;

    public MutableLiveData<List<Restaurant>> listLiveData = new MutableLiveData<>();

    private CollectionReference getRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public Retrofit getRetrofitInstance() {
        Retrofit retrofitInstance = restaurantService.retrofit;
        return retrofitInstance;
    }

    private RestaurantService getRestaurantService() {
        restaurantService = getRetrofitInstance().create(RestaurantService.class);
        return restaurantService;
    }

    public void getRestaurantsList() {
        this.getRestaurantService();
        this.getRetrofitInstance();
        restaurantService.getRestaurants(type, location, radius, key)
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
}
