package gabrielle.freville.go4lunch.model;

import com.google.firebase.Timestamp;

public class SelectedRestaurant {

    private int userId;
    private int restaurantId;
    private Timestamp date;
    private String name;

    public SelectedRestaurant(int userId, int restaurantId, Timestamp date, String name) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
        this.name = name;
    }

    //GETTERS//
    public int getUserId() {
        return userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    //SETTERS//
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
