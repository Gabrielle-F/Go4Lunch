package gabrielle.freville.go4lunch.model;

import android.media.Image;
import android.net.Uri;

public class User {

    private String firstName;
    private String lastName;
    private String id;
    private Uri picture;

    public User() { }

    public User(String firstName, String lastName, String id, Uri picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.picture = picture;
    }

    public User(String firstName, String id, Uri picture) {
        this.firstName = firstName;
        this.id = id;
        this.picture = picture;
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

    public Uri getUrlPicture() { return picture; }

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

    public void setUrlPicture(Uri picture) { this.picture = picture; }
}
