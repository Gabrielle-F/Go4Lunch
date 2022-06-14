package gabrielle.freville.go4lunch.utils;

import android.provider.SyncStateContract;

import com.google.android.gms.common.internal.Constants;

import java.util.List;

import gabrielle.freville.go4lunch.model.Restaurant;
import gabrielle.freville.go4lunch.model.response.RestaurantResponse;
import gabrielle.freville.go4lunch.repositories.RestaurantRepository;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("restaurant/{name}")
    Call<List<RestaurantResponse>> getNearbyRestaurantsList(
            @Query("name") String name,
            @Query("location") String location,
            @Query("type") String type,
            @Query("rating") int rating,
            @Query("user_ratings_total") int userRatingsTotal
    );

}
