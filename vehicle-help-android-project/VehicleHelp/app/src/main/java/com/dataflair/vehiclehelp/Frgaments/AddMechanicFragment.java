package com.dataflair.vehiclehelp.Frgaments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dataflair.vehiclehelp.Activities.AdminActivity;
import com.dataflair.vehiclehelp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class AddMechanicFragment extends Fragment {

    EditText mechanicNameEditTxt, mechanicNumberEditTxt, mechanicLocationEditTxt, mechanicPriceEditTxt, vehicleNameEditTxt;
    Button addMechanicDetailsBtn;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMechanicFragment() {
        // Required empty public constructor
    }

    public static AddMechanicFragment newInstance(String param1, String param2) {
        AddMechanicFragment fragment = new AddMechanicFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_mechanic, container, false);

        mechanicNameEditTxt = (EditText) view.findViewById(R.id.MechanicNameEditTxt);
        mechanicNumberEditTxt = (EditText) view.findViewById(R.id.MechanicNumberEditTxt);
        mechanicLocationEditTxt = (EditText) view.findViewById(R.id.LocationEditTxt);
        mechanicPriceEditTxt = (EditText) view.findViewById(R.id.PriceEditTxt);
        vehicleNameEditTxt = (EditText) view.findViewById(R.id.VehicleNameEditTxt);

        addMechanicDetailsBtn = (Button) view.findViewById(R.id.AddNotificationBtn);

        //Onlick implementation to get user details and to add it to database
        addMechanicDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting user details from edit Text
                String MechanicName = mechanicNameEditTxt.getText().toString().trim();
                String MechanicNumber = mechanicNumberEditTxt.getText().toString().trim();
                String MechanicLocaiton = mechanicLocationEditTxt.getText().toString().trim();
                String MechanicPrice = mechanicPriceEditTxt.getText().toString().trim();
                String vehicleName = vehicleNameEditTxt.getText().toString().trim();

                //Checking if there is any empty field or not
                if (MechanicName.isEmpty() || MechanicNumber.isEmpty() || MechanicLocaiton.isEmpty() || MechanicPrice.isEmpty() || vehicleName.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please,Enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    //Adding user detials to fireabase database
                    Add_Mechanic_Method(MechanicName, MechanicNumber, MechanicLocaiton, MechanicPrice, vehicleName);
                }
            }
        });


        return view;
    }

    private void Add_Mechanic_Method(String mechanicName, String mechanicNumber, String mechanicLocaiton, String mechanicPrice, String vehicleName) {


        String key = FirebaseDatabase.getInstance().getReference().child("mechanics").push().getKey();

        //Hashmap to store the userdetails and setting it to fireabse
        HashMap<String, Object> user_details = new HashMap<>();


        //Storing user details to hashMap
        user_details.put("mechanicLocation", mechanicLocaiton);
        user_details.put("mechanicNumber", mechanicNumber);
        user_details.put("mechanicPrice", mechanicPrice);
        user_details.put("vehicleName", vehicleName);
        user_details.put("mechanicName", mechanicName);

        String searchTxt = vehicleName.concat(mechanicLocaiton).toLowerCase();

        //updating the user details in firebase
        FirebaseDatabase.getInstance().getReference().child("mechanics").child(searchTxt).child(key)
                .updateChildren(user_details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference().child("allmechanics").child(key)
                            .updateChildren(user_details).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                //Setting all the Edit Text fields to null
                                mechanicNameEditTxt.setText("");
                                mechanicNumberEditTxt.setText("");
                                mechanicLocationEditTxt.setText("");
                                mechanicPriceEditTxt.setText("");
                                vehicleNameEditTxt.setText("");

                            }

                        }
                    });
                } else {
                    //showing message if there is failure in storing data to database
                    Toast.makeText(getContext(), "Please,Add details again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}