package cnmi.it.asthmaprototype;

import android.os.Bundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements Callback{
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if(savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragcontainer, Dashboard.class, null).commit();
//        }
<<<<<<< HEAD
        if(savedInstanceState != null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new FirstFragment()).commit();
        }
        bottomAppBar = findViewById(R.id.bottomAppBar);
//        bottomAppBar.setOnMenuItemClickListener(menuItem -> {
//            switch (menuItem.getItemId()){
//                case R.id.home:
//
//        });
        bottomAppBar.setOnMenuItemClickListener(menuItem ->{
            int itemid = menuItem.getItemId();

        });

        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.add).build();
=======
        bottomNavigationView = findViewById(R.id.btnnvg);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home, R.id.add).build();
>>>>>>> parent of 151879a (04032021-changes)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void someEvent(Fragment fragment) {
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}