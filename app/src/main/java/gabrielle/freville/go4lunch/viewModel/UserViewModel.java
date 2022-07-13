package gabrielle.freville.go4lunch.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.go4lunch.model.SelectedRestaurant;
import gabrielle.freville.go4lunch.model.User;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class UserViewModel extends androidx.lifecycle.ViewModel {

    private UserRepository userRepository;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(){
        userRepository.createUser();
    }

    public Task<User> getUserData() {
        return userRepository.getUserData().continueWith
                ((Continuation<DocumentSnapshot, User>) task -> task.getResult().toObject(User.class));
    }

    public Task<String> getFirstName(String firstName) {
        return userRepository.getFirstName(firstName).continueWith((Continuation<DocumentSnapshot, String>) task -> task.getResult().toString());
    }

    public Task<String> getEmail(String email) {
        return userRepository.getEmail(email).continueWith((Continuation<DocumentSnapshot, String>) task -> task.getResult().toString());
    }

    public Task<Void> deleteUser(Context context) {
        return userRepository.getCurrentUser().delete().addOnCompleteListener(task -> userRepository.deleteUserFromFirestore());
    }
}
