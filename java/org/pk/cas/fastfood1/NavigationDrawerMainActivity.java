package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationDrawerMainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_main);


        setSupportActionBar(toolbar);

        toolbar = findViewById(R.id.Toolbar);
        tabLayout = findViewById(R.id.TabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.NavigationView);
        drawerLayout = findViewById(R.id.DrawerLayout);
        firebaseAuth =FirebaseAuth.getInstance();


//        (Toggle button) means toolbar pr hmy jo three lines dakhye date ha.
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//       Default ToolBar item selected work
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int appBar = item.getItemId();
                if (appBar == R.id.shopping_toolbar_basket) {
                    Toast.makeText(NavigationDrawerMainActivity.this, "Home Pages", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NavigationDrawerMainActivity.this,ToolbarBasketActivity.class));
                }
                return true;
            }
        });

//      ViewPager
        viewPager2.setAdapter(new ViewPagerAdapter(this));
        
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Bryani");
                        break;
                    case 1:
                        tab.setText("Sandwich");
                        break;
                    case 2:
                        tab.setText("Pizza");
                        break;
                    case 3:
                        tab.setText("Zinger");
                        break;
                    case 4:
                        tab.setText("IceCream");
                        break;
                    default:
                        tab.setText("Shake");
                }
            }
        });tabLayoutMediator.attach();


//       NavigationDrawer ka andr waly item jn ka clickEvent pr work kr skty ha.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int data=item.getItemId();
                switch (data){
                    case R.id.home1:
                        Toast.makeText(NavigationDrawerMainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NavigationDrawerMainActivity.this,NavigationDrawerMainActivity.class));
                        break;
                    case R.id.MyCart:
                        Toast.makeText(NavigationDrawerMainActivity.this, "My Cart", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NavigationDrawerMainActivity.this,ToolbarBasketActivity.class));
                        break;
                    case R.id.MyOrder:
                        Toast.makeText(NavigationDrawerMainActivity.this, "My Order", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.SignOut:
                        Toast.makeText(NavigationDrawerMainActivity.this, "Account This SignOut.", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        /*SharedPreferences hm yaha pr sign out ka laya use kr raha ha */
                        final SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        startActivity(new Intent(NavigationDrawerMainActivity.this,MainActivity.class));
                        break;


                    case R.id.ProgrammerInfo:
                        Toast.makeText(NavigationDrawerMainActivity.this, "Information", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NavigationDrawerMainActivity.this,ProgrammerInfo.class));

                }
                return true;
            }
        });
    }
}