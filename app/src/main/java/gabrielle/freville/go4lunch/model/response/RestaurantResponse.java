package gabrielle.freville.go4lunch.model.response;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantResponse {

    public class Geometry {

        @SerializedName("location")
        @Expose
        private Geometry location;

        public Geometry(Geometry location) {
            this.location = location;
        }
    }

    public class Name {

        @SerializedName("name")
        @Expose
        private String name;

        public Name(String name) {
            this.name = name;
        }
    }

    public class OpeningHours {

        @SerializedName("opening_hours")
        @Expose
        private Boolean opennow;

        public Boolean getOpennow() { return opennow; }
    }

    public class Type {

        @SerializedName("type")
        @Expose
        private String type;

        public String getType() { return type; }
    }

    public class Photos {

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

    public class Rating {

        @SerializedName("rating")
        @Expose
        private int rating;

        public int getRating() { return rating; }
    }

    public class UserRatingsTotal {

        @SerializedName("user_ratings_total")
        @Expose
        private int userRatingsTotal;

        public int getUserRatingsTotal() { return userRatingsTotal; }
    }

    public class AdressComponents {

        @SerializedName("long_name")
        @Expose
        private String longName;

        public String getLongName() { return longName; }
    }
}
