package gabrielle.freville.go4lunch.repositories;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RestaurantRepository {

    private static final String COLLECTION_NAME = "restaurants";

    private CollectionReference getRestaurantsCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
}
