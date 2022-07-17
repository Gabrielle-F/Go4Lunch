package gabrielle.freville.go4lunch.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.UnknownServiceException;
import java.util.List;
import java.util.Objects;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.injections.Injection;
import gabrielle.freville.go4lunch.injections.ViewModelFactory;
import gabrielle.freville.go4lunch.model.User;
import gabrielle.freville.go4lunch.viewModel.UserViewModel;

public class WorkmatesListFragment extends Fragment {

    private List<User> users;
    private WorkmatesRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private UserViewModel userViewModel;
    private LiveData<List<User>> liveData;

    public WorkmatesListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workmates_list, container, false);

        recyclerView = view.findViewById(R.id.list_workmates_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WorkmatesRecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);

        configureViewModel();
        liveData = userViewModel.getUsers();
        Objects.requireNonNull(liveData).observe(getViewLifecycleOwner(), this::initList);

        return view;
    }

    private void configureViewModel() {
        ViewModelFactory factory = Injection.provideViewModelFactory(getContext());
        this.userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);
    }

    private void initList(List<User> usersList) {
        adapter.updateWorkmatesList(usersList);
    }
}