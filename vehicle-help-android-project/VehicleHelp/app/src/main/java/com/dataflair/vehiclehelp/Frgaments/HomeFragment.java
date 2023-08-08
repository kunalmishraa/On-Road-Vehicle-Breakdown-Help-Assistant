package com.dataflair.vehiclehelp.Frgaments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dataflair.vehiclehelp.Adapters.HomeAdapter;
import com.dataflair.vehiclehelp.Model.Model;
import com.dataflair.vehiclehelp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This Fragment is used to display the exam notifications in home page
 * we use Recycler view to Display the data in this fragment
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter adapter;
    Button searchBtn;
    EditText vehicleNameEditTxt, locationEditTxt;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Assigning the Recyclerview to diapsly notificaitons
        recyclerView = (RecyclerView) view.findViewById(R.id.HomeRecyclerVeiw);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        locationEditTxt = (EditText) view.findViewById(R.id.LocationEditTxtHome);
        vehicleNameEditTxt = (EditText) view.findViewById(R.id.VehicleEditTxtHome);


        searchBtn = (Button) view.findViewById(R.id.SearchBtn);
        //Implementing on ClickListener to search data
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting data from editText
                String location = locationEditTxt.getText().toString().toLowerCase();
                String vehicle = vehicleNameEditTxt.getText().toString().toLowerCase();
                //checking if there is empty field
                if (location.isEmpty() || vehicle.isEmpty()) {
                    //Showing toast message if there is any empty field
                    Toast.makeText(getContext(), "Please,Fill details", Toast.LENGTH_SHORT).show();
                } else {

                    //Firebase Recycler Options to get the data form firebase database using model class and reference
                    FirebaseRecyclerOptions<Model> options =
                            new FirebaseRecyclerOptions.Builder<Model>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("mechanics").child(vehicle.concat(location)), Model.class)
                                    .build();


                    adapter = new HomeAdapter(options);
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();

                }

            }
        });

        //Firebase Recycler Options to get the data form firebase database using model class and reference
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("allmechanics"), Model.class)
                        .build();


        adapter = new HomeAdapter(options);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Starts listening for data from firebase when this fragment starts
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        //Stops listening for data from firebase
        adapter.stopListening();
    }
}