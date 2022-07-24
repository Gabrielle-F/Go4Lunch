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

    @SerializedName("place_id")
    @Expose
    private String placeId;

    @SerializedName("website")
    @Expose
    private String websiteUrl;

    @SerializedName("formatted_phone_number")
    @Expose
    private String phoneNumber;

    public RestaurantResponse(String name, Boolean opennow, String address, int rating, int userRatingsTotal, String photoReference, String website, String phoneNumber) {
        this.name = name;
        this.opennow = opennow;
        this.address = address;
        this.rating = rating;
        this.userRatingsTotal = userRatingsTotal;
        this.photoReference = photoReference;
        this.websiteUrl = website;
        this.phoneNumber = phoneNumber;
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
    public String getLocation() { return location; }
    public String getPlaceId() { return placeId; }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // SETTERS //

    public void setHtmlAttributions(List<Object> htmlAttributions) { this.htmlAttributions = htmlAttributions; }
    public void setPhotoReference(String photoReference) { this.photoReference = photoReference; }
    public void setHeight(int height) { this.height = height; }
    public void setWidth(int width) { this.width = width; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpennow(Boolean opennow) {
        this.opennow = opennow;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUserRatingsTotal(int userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
