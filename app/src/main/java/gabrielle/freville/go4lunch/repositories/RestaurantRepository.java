package gabrielle.freville.go4lunch.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.utils.RestaurantService;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
    public LiveData<List<RestaurantResponse>> listLiveData;

    private CollectionReference getRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    private RestaurantService getRestaurantService() {
        restaurantService = getRetrofitInstance().create(RestaurantService.class);
        return restaurantService;
    }

    public void showResult() {
        this.getRestaurantService();
        this.getRetrofitInstance();
        restaurantService.getRestaurants(name, type, geometry, opennow, openingHours)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RestaurantResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<RestaurantResponse> restaurantResponses) {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "Return restaurant list");
                    }
                });
    }

    public Retrofit getRetrofitInstance() {
        Retrofit retrofitInstance = restaurantService.retrofit;
        return retrofitInstance;
    }
}
