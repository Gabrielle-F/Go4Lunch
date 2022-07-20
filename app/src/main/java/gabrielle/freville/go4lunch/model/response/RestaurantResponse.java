package gabrielle.freville.go4lunch.model.response;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    @SerializedName("opening_hours")
    @Expose
    private Boolean opennow;

    public RestaurantResponse(String name, Boolean opennow, String address) {
        this.name = name;
        this.opennow = opennow;
        this.vicinity = address;
    }

    //GETTERS//


    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public Boolean getOpennow() {
        return opennow;
    }

    public class Geometry {

        @SerializedName("location")
        @Expose
        private Geometry location;

        public Geometry(Geometry location) {
            this.location = location;
        }
    }

    public static class Name {

        @SerializedName("name")
        @Expose
        private String name;

        public Name(String name) {
            this.name = name;
        }
    }

    public static class OpeningHours {

        @SerializedName("opening_hours")
        @Expose
        private Boolean opennow;

        public Boolean getOpennow() { return opennow; }
    }

    public static class Type {

        @SerializedName("type")
        @Expose
        private String type;

        public String getType() { return type; }
    }

    public static class Photos {

        @SerializedName("html_attributions")
        @Expose
        private List<Object> htmlAttributions = null;
        @SerializedName("photo_reference")
        @Expose
        private String photoReference;
        private int height;
        private int width;

        public List<Object> getHtmlAttributions() { return htmlAttributions; }
        public void setHtmlAttributions(List<Object> htmlAttributions) { this.htmlAttributions = htmlAttributions; }
        public String getPhotoReference() { return photoReference; }
        public void setPhotoReference(String photoReference) { this.photoReference = photoReference; }
        public int getHeight() { return height; }
        public void setHeight(int height) { this.height = height; }
        public int getWidth() { return width; }
        public void setWidth(int width) { this.width = width; }
    }

    public static class Rating {

        @SerializedName("rating")
        @Expose
        private int rating;

        public int getRating() { return rating; }
    }

    public static class UserRatingsTotal {

        @SerializedName("user_ratings_total")
        @Expose
        private int userRatingsTotal;

        public int getUserRatingsTotal() { return userRatingsTotal; }
    }
}
