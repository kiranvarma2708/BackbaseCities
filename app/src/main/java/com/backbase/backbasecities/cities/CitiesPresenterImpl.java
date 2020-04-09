package com.backbase.backbasecities.cities;

import android.content.Context;
import android.os.Handler;
import com.backbase.backbasecities.models.Cities;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;

public class CitiesPresenterImpl implements CitiesInterface.CitiesListPresenter{

    private WeakReference<CitiesInterface.CitiesListView> citiesListViewRef;
    private CitiesJsonImpl citiesRepository;

    public CitiesPresenterImpl(CitiesInterface.CitiesListView citiesListView, @NonNull Context context){
        this.citiesListViewRef = new WeakReference<>(citiesListView);
        this.citiesRepository = new CitiesJsonImpl(this, context);
    }

    @Override
    public void fetchCitiesList() {

        CitiesInterface.CitiesListView aboutViewImpl = citiesListViewRef.get();
        aboutViewImpl.showProgress();
        new Handler().postDelayed(() -> citiesRepository.getCitiesList(), 1000);
    }

    @Override
    public void onSuccess(ArrayList<Cities> citiesList) {

        CitiesInterface.CitiesListView citiesListView = citiesListViewRef.get();

        if(citiesListView != null){
            citiesListView.hideProgress();
            citiesListView.setCitiesList(citiesList);
        }
    }

    @Override
    public void onFail() {
        CitiesInterface.CitiesListView citiesListView = citiesListViewRef.get();
        if (citiesListView != null){
            citiesListView.hideProgress();
            citiesListView.showError();
        }
    }


}
