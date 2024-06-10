package com.example.lab6_20200638_iot;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        replaceFragment(new IngresosFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int menuItemId =  item.getItemId();

            if (menuItemId ==  R.id.ingresos) {
                replaceFragment(new IngresosFragment());
                return true;
            }
            else if (menuItemId ==  R.id.egresos) {
                replaceFragment(new EgresosFragment());
                return true;
            }
            else if (menuItemId ==  R.id.resumen) {
                replaceFragment(new ResumenFragment());
                return true;
            }
            else if (menuItemId ==  R.id.perfil) {
                replaceFragment(new PerfilFragment());
                return true;
            }
            return true;
        });
    }

    private  void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container1, fragment);
        transaction.commit();

    }
}