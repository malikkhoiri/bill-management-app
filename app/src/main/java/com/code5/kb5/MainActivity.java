package com.code5.kb5;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.code5.kb5.feature.history.HistoryFragment;
import com.code5.kb5.feature.profile.ProfileFragment;
import com.code5.kb5.feature.tagihan.TagihanFragment;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tagihan:
//                    getSupportActionBar().setTitle(R.string.title_tagihan);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_content, new TagihanFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    return true;
                case R.id.navigation_history:
//                    getSupportActionBar().setTitle(R.string.title_history);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_content, new HistoryFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    return true;
                case R.id.navigation_profile:
//                    getSupportActionBar().setTitle(R.string.title_profile);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_content, new ProfileFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
