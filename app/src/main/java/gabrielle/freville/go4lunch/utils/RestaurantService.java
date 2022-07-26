package gabrielle.freville.go4lunch.utils;

import android.location.Location;

import java.util.List;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    /** Get nearby places **/
    @GET("nearbysearch/json?")
    Observable<List<RestaurantResponse>> getRestaurants(
            @Query("type") String type,
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("key") int key
    );

    /** Get nearby places matching the keyword searched by the user */
    @GET("nearbysearch/json?")
    Observable<List<RestaurantResponse>> getRestaurantsFromUserResearch(
            @Query("type") String type,
            @Query("location") Location location,
            @Query("radius") int radius,
            @Query("key") int key,
            @Query("keyword") String restaurantName
    );


    /** Get Restaurant Details */
    @GET("details/json?")
    Observable<RestaurantResponse> getRestaurantDetails(
            @Query("key") int key,
            @Query("place_id") String placeId
    );

}
