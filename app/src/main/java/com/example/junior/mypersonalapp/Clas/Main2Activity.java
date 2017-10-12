package com.example.junior.mypersonalapp.Clas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junior.mypersonalapp.R;
import com.example.junior.mypersonalapp.model.User;
import com.example.junior.mypersonalapp.repository.UserRepository;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private TextView usernameText;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Dashboard Activity
        usernameText = (TextView)findViewById(R.id.fullname_text);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(user.getFullname());


        // Setear Toolbar como action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Set DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set drawer toggle icon
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Set NavigationItemSelectedListener
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Do action by menu item id
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(Main2Activity.this, "Inicio", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_data:
                        Toast.makeText(Main2Activity.this, "Datos", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(Main2Activity.this, "Estas en configuracion...", Toast.LENGTH_SHORT).show();
                        preferences();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(Main2Activity.this, "Cerrar sesion", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(Main2Activity.this, MainActivity.class));
                        callLogout();
                        break;
                }

                // Change navigation header information
                ImageView photoImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.menu_photo);
                photoImage.setBackgroundResource(R.drawable.ic_profile);

                TextView fullnameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_fullname);
                fullnameText.setText("Erick Benites");

                TextView emailText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_email);
                emailText.setText("ebenites@tecsup.edu.pe");


                // Close drawer
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);   // Open drawer
                else
                    drawerLayout.closeDrawer(GravityCompat.START);    // Close drawer
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void callLogout(){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

        finish();
    }
    public void preferences(){
        startActivity(new Intent(Main2Activity.this, MyPreferencesActivity.class));
    }

}
