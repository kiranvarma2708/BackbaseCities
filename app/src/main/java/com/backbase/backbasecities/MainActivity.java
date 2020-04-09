package com.backbase.backbasecities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.backbase.backbasecities.cities.CitiesListFragment;
import com.backbase.backbasecities.models.Cities;

public class MainActivity extends AppCompatActivity implements CitiesListFragment.ListenerOnCitiesSelected {
    private MapFragment cityLocationMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityLocationMapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
    }

    @Override
    public void onSelectedCities(Cities city) {
        if (!getResources().getBoolean(R.bool.screen_orientation_landscape)) {
            cityLocationMapFragment = MapFragment.newInstance(city);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, cityLocationMapFragment, cityLocationMapFragment.getTag())
                    .addToBackStack(null)
                    .commit();
        } else {
            cityLocationMapFragment.updateCityLocationOnMap(city);
        }
    }
}
