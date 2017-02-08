package ua.dp.oleg.maliy.lexicoach;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    public static final String AVATAR_URL = "http://lorempixel.com/200/200/people/1/";


    private DrawerLayout drawerLayout;
    private View content;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initToolbar();
        setupDrawerLayout();

        content = findViewById(R.id.content);
        drawerToggle = setupDrawerToggle();

//        final ImageView avatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.avatar);
//        Picasso.with(this).load(AVATAR_URL).transform(new CircleTransform()).into(avatar);

    }


    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_grey);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerLayout() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.buttonAdd:
                        Toast.makeText(MainActivity.this, getString(R.string.button_Add), Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);
                        finish();
                        break;
                    case R.id.buttonOwn:
                        Toast.makeText(MainActivity.this, getString(R.string.button_Own), Toast.LENGTH_SHORT).show();
//                        Intent j = new Intent(getApplicationContext(), SecondActivity.class);
//                        startActivity(j);
                        finish();
                        break;
                }
                return true;
            }
        });
    }
}