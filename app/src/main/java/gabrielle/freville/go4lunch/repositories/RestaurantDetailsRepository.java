package gabrielle.freville.go4lunch.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

public class RestaurantDetailsRepository {

    private final String COLLECTION_NAME = "restaurants";
    private RestaurantService restaurantService;
    private Restaurant restaurant = new Restaurant();

    private int key = R.string.google_maps_key;

    public MutableLiveData<Restaurant> restaurantDetails = new MutableLiveData<>();

    public Retrofit getRetrofitInstance() {
        Retrofit retrofitInstance = restaurantService.retrofit;
        return retrofitInstance;
    }

    private CollectionReference getRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public void getRestaurantDetails(String placeId) {
        getRetrofitInstance();
        restaurantService.getRestaurantDetails(key, placeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<RestaurantResponse, Restaurant>() {
                    @Override
                    public Restaurant apply(@NonNull RestaurantResponse restaurantResponse) throws Exception {
                        Restaurant restaurantInstance = new Restaurant(
                                restaurantResponse.getName(),
                                restaurantResponse.getAddress(),
                                restaurantResponse.getPhotoReference(),
                                restaurantResponse.getRating(),
                                restaurantResponse.getWebsiteUrl(),
                                restaurantResponse.getPhoneNumber()
                        );
                        return restaurantInstance;
                    }
                })
                .subscribe(new Observer<Restaurant>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Restaurant restaurant) {
                        restaurantDetails.setValue(restaurant);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<Restaurant> getRestaurantId(String id) {
        MutableLiveData<gabrielle.freville.go4lunch.model.Restaurant> restaurant = new MutableLiveData<>();
        getRestaurantsCollection().document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    gabrielle.freville.go4lunch.model.Restaurant restaurantInstance = task.getResult().toObject(gabrielle.freville.go4lunch.model.Restaurant.class);
                    restaurant.setValue(restaurantInstance);
                }
            }
        });
        return restaurant;
    }

    public LiveData<Restaurant> getRestaurantDetails() {
        return restaurantDetails;
    }
}
