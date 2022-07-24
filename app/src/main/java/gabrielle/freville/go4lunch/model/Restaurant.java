package gabrielle.freville.go4lunch.model;

public class Restaurant {

    private String name;

    private int stars;
    private String address;
    private String openingHours;
    private String distance;
    private Boolean opennow;
    private int rating;
    private int userRatingsTotal;
    private String photoReference;
    private String placeId;
    private String website;
    private String phoneNumber;

    public Restaurant() { }

    public Restaurant(String name, int rating, String address, Boolean openNow) {
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.opennow = openNow;
    }

    public Restaurant(String name, String address, String photoReference, int rating, String website, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.photoReference = photoReference;
        this.rating = rating;
        this.website = website;
        this.phoneNumber = phoneNumber;
    }

    public Restaurant(String name, String address, Boolean opennow, int rating, int userRatingsTotal, String photoReference) {
        this.name = name;
        this.address = address;
        this.opennow = opennow;
        this.rating = rating;
        this.userRatingsTotal = userRatingsTotal;
        this.photoReference = photoReference;
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

    public String getOpeningHours() {
        return openingHours;
    }

    public String getDistance() { return distance; }

    public int getUserRatingsTotal() { return userRatingsTotal; }

    public int getRating() { return rating; }

    public Boolean getOpennow() {
        return opennow;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

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

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setDistance(String distance) { this.distance = distance; }

    public void setOpennow(Boolean opennow) {
        this.opennow = opennow;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUserRatingsTotal(int userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
