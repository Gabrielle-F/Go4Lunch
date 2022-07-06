package gabrielle.freville.go4lunch.model;

import android.media.Image;
import android.net.Uri;

public class User {

    private String firstName;
    private String lastName;
    private String id;
    private String pictureUrl;
    private String email;

    public User() { }

    public User(String firstName, String lastName, String id, String pictureUrl, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.email = email;
    }

    public User(String firstName, String id, String pictureUrl) {
        this.firstName = firstName;
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    // GETTERS //
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getUrlPicture() { return pictureUrl; }

    // SETTERS //
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrlPicture(String pictureUrl) { this.pictureUrl = pictureUrl; }
}
