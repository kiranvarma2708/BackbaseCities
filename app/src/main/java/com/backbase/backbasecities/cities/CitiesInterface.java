package com.backbase.backbasecities.cities;

import com.backbase.backbasecities.models.Cities;
import java.util.ArrayList;

public interface CitiesInterface {
    interface CitiesRepository {
        void getCitiesList();
    }

    interface CitiesListPresenter {
        void fetchCitiesList();
        void onSuccess(ArrayList<Cities> citiesList);
        void onFail();
    }

    interface CitiesListView {
        void setCitiesList(ArrayList<Cities> citiesList);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
