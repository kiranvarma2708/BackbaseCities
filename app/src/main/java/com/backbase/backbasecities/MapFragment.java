package com.backbase.backbasecities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.backbase.backbasecities.about.AboutActivity;
import com.backbase.backbasecities.models.Cities;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private static final String CITIES_LIST = "cities_list";
    private GoogleMap mMap;
    private Cities city;
    private float zoom = 8f;

    static MapFragment newInstance(Cities city) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable(CITIES_LIST, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle args = getArguments();
        if (args != null) {
            city = args.getParcelable(CITIES_LIST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); // Hiding the keyboard to view the map in full screen.

        View headerBar = rootView.findViewById(R.id.header_bar);
        if (getResources().getBoolean(R.bool.screen_orientation_landscape)) {
            headerBar.setVisibility(View.GONE);
        }
        final View backButton = headerBar.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> Objects.requireNonNull(getActivity()).onBackPressed());

        final View buttonInfo = rootView.findViewById(R.id.about_info);
        buttonInfo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AboutActivity.class);
            intent.putExtra("name", city.getName());
            intent.putExtra("country", city.getCountry());
            intent.putExtra("coord", city.getLatitude()+","+city.getLongitude());
            intent.putExtra("id", city.getId());
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        showLocationonMap();
    }

    void updateCityLocationOnMap(Cities city) {
        this.city = city;
        showLocationonMap();
    }

    private void showLocationonMap() {
        if (city == null) {
            return;
        }
        mMap.clear();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        LatLng location = new LatLng(city.getLatitude(), city.getLongitude());
        Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(city.getName()+", "+city.getCountry()));
        marker.setTag(city);
        builder.include(location);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, zoom));
        mMap.getUiSettings().setMapToolbarEnabled(false);
    }
}
