package com.example.loginandsignupapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.loginandsignupapp.R;
import com.example.loginandsignupapp.viewmodel.LoggedInViewModel;

public class LoggedInFragment extends Fragment {
    private Button logOutButton;

    private LoggedInViewModel loggedInViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loggedInViewModel = new ViewModelProvider(this).get(LoggedInViewModel.class);

        String items[]=new String[]{"name","city","age","gender"};

        ListView listView=(ListView) findViewById(R.id.list_items);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnClickListener(new AdapterView.OnItemClickListener())
        {
            public void onitemClick(AdapterView<?> parent,View view ,int i,long id)
            {

            }
        }



        loggedInViewModel.getLoggedOutLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if (loggedOut) {
                    Toast.makeText(getContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_loggedInFragment_to_loginRegisterFragment);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loggedin, container, false);


        logOutButton = view.findViewById(R.id.fragment_loggedin_logOut);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInViewModel.logOut();
            }
        });


        return view;
    }
}

