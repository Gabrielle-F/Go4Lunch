package gabrielle.freville.go4lunch.viewModel;

import android.content.Context;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import gabrielle.freville.go4lunch.model.User;
import gabrielle.freville.go4lunch.repositories.UserRepository;

public class UserViewModel {

    private UserRepository userRepository;

    public void createUser(){
        userRepository.createUser();
    }

    public Task<User> getUserData() {
        return userRepository.getUserData().continueWith
                ((Continuation<DocumentSnapshot, User>) task -> task.getResult().toObject(User.class));
    }

    public Task<Void> deleteUser(Context context) {
        return userRepository.getCurrentUser().delete().addOnCompleteListener(task -> userRepository.deleteUserFromFirestore());
    }
}
