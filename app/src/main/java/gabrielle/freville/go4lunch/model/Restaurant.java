package gabrielle.freville.go4lunch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("name")
    @Expose
    private String name;

    private int stars;
    private String address;
    private String hours;
    private String distance;
    private Boolean opennow;

    public Restaurant() { }

    public Restaurant(String name, int stars, String address, String hours, String distance) {
        this.name = name;
        this.stars = stars;
        this.address = address;
        this.hours = hours;
        this.distance = distance;
    }

    public Restaurant(String name, String address, Boolean opennow) {
        this.name = name;
        this.address = address;
        this.opennow = opennow;
    }

    // GETTERS //
    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public String getAddress() {
        return address;
    }

    public String getHours() {
        return hours;
    }

    public String getDistance() { return distance; }

    // SETTERS //
    public void setName(String name) {
        this.name = name;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDistance(String distance) { this.distance = distance; }
}
