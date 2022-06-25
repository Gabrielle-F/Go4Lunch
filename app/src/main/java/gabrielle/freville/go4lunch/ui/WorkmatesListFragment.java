package gabrielle.freville.go4lunch.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gabrielle.freville.go4lunch.R;

public class WorkmatesListFragment extends Fragment {

    public WorkmatesListFragment() {
    }

    public static WorkmatesListFragment newInstance() {
        WorkmatesListFragment fragment = new WorkmatesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_workmates_list, container, false);
    }
}