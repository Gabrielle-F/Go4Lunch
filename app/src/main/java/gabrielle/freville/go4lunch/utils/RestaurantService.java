package gabrielle.freville.go4lunch.utils;

import java.util.List;

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

    @GET("nearbysearch/json")
    Observable<List<RestaurantResponse>> getRestaurants(
            @Query("name") String name,
            @Query("type") String type,
            @Query("geometry") String geometry,
            @Query("open_now") Boolean opennow,
            @Query("opening_hours") String openingHours,
            @Query("vicinity") String vicinity
    );

}
