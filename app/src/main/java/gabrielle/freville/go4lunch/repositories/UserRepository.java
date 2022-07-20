package gabrielle.freville.go4lunch.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.go4lunch.model.SelectedRestaurant;
import gabrielle.freville.go4lunch.model.User;

public class UserRepository {

    private static final String COLLECTION_NAME = "users";
    private static final String SELECTED_RESTAURANTS_COLLECTION = "selectedRestaurants";
    private static final String USERNAME_FIELD = "firstName";
    private static final String SELECTED_RESTAURANT_ID = "restaurantId";
    private static final String USER_ID = "userId";
    private static final String USER_NOTIFICATIONS = "notifications";
    private static final String TAG = "Users List";
    private MutableLiveData<List<User>> usersList = new MutableLiveData<>();
    private MutableLiveData<List<SelectedRestaurant>> selectedRestaurantsList = new MutableLiveData<>();

    private CollectionReference getUsersCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    private CollectionReference getSelectedRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(SELECTED_RESTAURANTS_COLLECTION);
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

    public Task<DocumentSnapshot> getFirstName(String firstName) {
        String uid = this.getCurrentUser().getUid();
        if (uid != null) {
            return this.getUsersCollection().document(firstName).get();
        } else {
            return null;
        }
    }

    public Task<DocumentSnapshot> getEmail(String email) {
        String uid = this.getCurrentUser().getUid();
        if (uid != null) {
            return this.getUsersCollection().document(email).get();
        } else {
            return null;
        }
    }

    public Task<DocumentSnapshot> getProfiltePictureUri(String profilePicture) {
        String uid = this.getCurrentUser().getUid();
        if (uid != null) {
            return this.getUsersCollection().document(profilePicture).get();
        } else {
            return null;
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

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void deleteUserFromFirestore() {
        String uid = getCurrentUser().getUid();
        if (uid != null) {
            this.getUsersCollection().document(uid).delete();
        }
    }

    public MutableLiveData<List<User>> getUsersList() {
        getUsersCollection().get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<User> listUsers = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listUsers.add(document.toObject(User.class));
                    }
                    usersList.postValue(listUsers);
                    Log.d(TAG, listUsers.toString());
                } else {
                    Log.d(TAG, "Error getting documents : ", task.getException());
                }
            }
        });
        return usersList;
    }

    public MutableLiveData<List<SelectedRestaurant>> getSelectedRestaurants() {
        getSelectedRestaurantsCollection().get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<SelectedRestaurant> listSelectedRestaurants = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listSelectedRestaurants.add(document.toObject(SelectedRestaurant.class));
                    }
                    selectedRestaurantsList.postValue(listSelectedRestaurants);
                }
            }
        });
        return selectedRestaurantsList;
    }

    public Task<DocumentSnapshot> getSelectedRestaurantId() {
        return this.getSelectedRestaurantsCollection().document(SELECTED_RESTAURANT_ID).get();
    }

    public Task<DocumentSnapshot> getSelectedRestaurantName(String name) {
        Task<DocumentSnapshot> id = getSelectedRestaurantId();
        if (id != null) {
            return this.getSelectedRestaurantsCollection().document(name).get();
        } else {
            return null;
        }
    }

    public Task<DocumentSnapshot> getUserId() {
        return this.getSelectedRestaurantsCollection().document(USER_ID).get();
    }

    public Task<DocumentSnapshot> getNotifications() {
        String uid = this.getCurrentUser().getUid();
        if (uid != null) {
            return this.getUsersCollection().document(USER_NOTIFICATIONS).get();
        } else {
            return null;
        }
    }

}
