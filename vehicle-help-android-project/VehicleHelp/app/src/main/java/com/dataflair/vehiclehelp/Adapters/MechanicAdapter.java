package com.dataflair.vehiclehelp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dataflair.vehiclehelp.Model.Model;
import com.dataflair.vehiclehelp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MechanicAdapter extends FirebaseRecyclerAdapter<Model, MechanicAdapter.Viewholder> {

    public MechanicAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull MechanicAdapter.Viewholder holder, int position, @NonNull Model model) {


        //Adding data to xml file
        holder.nameTxt.setText(model.getMechanicName());
        holder.phoneNumberTxt.setText(model.getMechanicNumber());
        holder.LocationTxt.setText(model.getMechanicLocation());
        holder.priceTxt.setText(model.getMechanicPrice());
        holder.vechicleNameTxt.setText(model.getVehicleName());


    }


    @NonNull
    @Override
    public MechanicAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //the data objects are inflated into the xml file single_data_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_data_file, parent, false);
        return new MechanicAdapter.Viewholder(view);
    }

    //we need view holder to hold each objet form recyclerview and to show it in recyclerview
    class Viewholder extends RecyclerView.ViewHolder {


        TextView nameTxt, phoneNumberTxt, vechicleNameTxt, LocationTxt, priceTxt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            //asssiginig the address of the android materials to show  mechanic Details
            nameTxt = (TextView) itemView.findViewById(R.id.NameTxt);
            phoneNumberTxt = (TextView) itemView.findViewById(R.id.PhoneNumberTxt);
            vechicleNameTxt = (TextView) itemView.findViewById(R.id.VehicleNameTxt);
            LocationTxt = (TextView) itemView.findViewById(R.id.LocationTxt);
            priceTxt = (TextView) itemView.findViewById(R.id.PriceTxt);

        }
    }

}


