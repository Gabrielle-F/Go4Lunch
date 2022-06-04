package gabrielle.freville.go4lunch;

public class Restaurant {

    private String name;
    private int stars;
    private String address;
    private String hours;

    public Restaurant() { }

    public Restaurant(String name, int stars, String address, String hours) {
        this.name = name;
        this.stars = stars;
        this.address = address;
        this.hours = hours;
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
}
