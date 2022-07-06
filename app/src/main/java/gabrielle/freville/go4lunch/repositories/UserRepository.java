package gabrielle.freville.go4lunch.repositories;

import android.media.Image;
import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import gabrielle.freville.go4lunch.model.User;

public class UserRepository {

    private static final String COLLECTION_NAME = "users";
    private static final String USERNAME_FIELD = "first_name";

    private CollectionReference getUsersCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public void createUser() {
        FirebaseUser user = getCurrentUser();
        if (user != null) {
            String urlPicture = (user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null;
            String firstName = user.getDisplayName();
            String lastName = user.getDisplayName();
            String email = user.getEmail();
            String uid = user.getUid();

            User userToCreate = new User(firstName, lastName, uid, urlPicture, email);

            Task<DocumentSnapshot> userData = getUserData();
            userData.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.contains(USERNAME_FIELD)) {
                        userToCreate.setFirstName((String) documentSnapshot.get(USERNAME_FIELD));
                    }
                    getUsersCollection().document(uid).set(userToCreate);
                }
            });
        }
    }

    public Task<DocumentSnapshot> getUserData() {
        String uid = this.getCurrentUser().getUid();
        if (uid != null) {
            return this.getUsersCollection().document(uid).get();
        } else {
            return null;
        }
    }

    public FirebaseUser getCurrentUser() { return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void deleteUserFromFirestore() {
        String uid = getCurrentUser().getUid();
        if (uid != null) {
            this.getUsersCollection().document(uid).delete();
        }
    }

}
