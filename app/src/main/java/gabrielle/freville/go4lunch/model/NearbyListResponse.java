package gabrielle.freville.go4lunch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gabrielle.freville.go4lunch.model.response.RestaurantResponse;

public class NearbyListResponse {

    private List<RestaurantResponse> results;

    public List<RestaurantResponse> getResult() { return results; }

    public void setResult(List<RestaurantResponse> results) { this.results = results; }
}
