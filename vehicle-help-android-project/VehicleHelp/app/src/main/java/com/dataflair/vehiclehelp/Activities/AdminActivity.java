package com.dataflair.vehiclehelp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.dataflair.vehiclehelp.Frgaments.AddMechanicFragment;
import com.dataflair.vehiclehelp.Frgaments.ShowMechanicFragment;
import com.dataflair.vehiclehelp.Frgaments.UserProfileFragment;
import com.dataflair.vehiclehelp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        //Assigning frameLayout resource file to show appropriate fragment using address
        frameLayout = (FrameLayout) findViewById(R.id.FragmentContainerAdmin);
        //Assigining BottomNavigaiton Menu
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationViewAdmin);
        Menu menuNav = bottomNavigationView.getMenu();
        //Setting the default fragment as HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainerAdmin, new AddMechanicFragment()).commit();
        //Calling the bottoNavigationMethod when we click on any menu item
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationMethod);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                    //Assigining Fragment as Null
                    Fragment fragment = null;
                    switch (item.getItemId()) {

                        //Shows the Appropriate Fragment by using id as address
                        case R.id.addMechanicMenu:
                            fragment = new AddMechanicFragment();
                            break;
                        case R.id.allMechanicsMenu:
                            fragment = new ShowMechanicFragment();
                            break;
                        case R.id.profileMenu:
                            fragment = new UserProfileFragment();
                            break;


                    }
                    //Sets the selected Fragment into the Framelayout
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainerAdmin, fragment).commit();
                    return true;
                }
            };
}