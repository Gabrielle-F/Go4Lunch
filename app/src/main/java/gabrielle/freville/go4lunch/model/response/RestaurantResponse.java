package gabrielle.freville.go4lunch.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("vicinity")
    @Expose
    private String address;

    @SerializedName("opening_hours")
    @Expose
    private Boolean opennow;

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("photo_reference")
    @Expose
    private String photoReference;
    private int height;
    private int width;

    @SerializedName("rating")
    @Expose
    private int rating;

    @SerializedName("user_ratings_total")
    @Expose
    private int userRatingsTotal;

    @SerializedName("location")
    @Expose
    private String location;

    public RestaurantResponse(String name, Boolean opennow, String address, int rating, int userRatingsTotal, String photoReference) {
        this.name = name;
        this.opennow = opennow;
        this.address = address;
        this.rating = rating;
        this.userRatingsTotal = userRatingsTotal;
        this.photoReference = photoReference;
    }

    //GETTERS//

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public Boolean getOpennow() {
        return opennow;
    }
    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public String getPhotoReference() { return photoReference; }
    public List<Object> getHtmlAttributions() { return htmlAttributions; }
    public int getRating() { return rating; }
    public int getUserRatingsTotal() { return userRatingsTotal; }

    // SETTERS //

    public void setHtmlAttributions(List<Object> htmlAttributions) { this.htmlAttributions = htmlAttributions; }
    public void setPhotoReference(String photoReference) { this.photoReference = photoReference; }
    public void setHeight(int height) { this.height = height; }
    public void setWidth(int width) { this.width = width; }
}
