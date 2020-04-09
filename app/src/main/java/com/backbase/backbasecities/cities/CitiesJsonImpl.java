package com.backbase.backbasecities.cities;

import android.content.Context;
import android.content.res.AssetManager;
import com.backbase.backbasecities.models.Cities;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;

public class CitiesJsonImpl implements CitiesInterface.CitiesRepository {
    private final CitiesInterface.CitiesListPresenter presenter;
    private final WeakReference<Context> context;
    private static final String FILE_NAME = "cities.json";
    private ArrayList<Cities> citiesList;

    CitiesJsonImpl(@NonNull CitiesInterface.CitiesListPresenter presenter, @NonNull Context context){
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getCitiesList() {

        if (citiesList == null) {
            String citiesJson = getCityListInfoFromAssets();
            if(citiesJson != null && !citiesJson.isEmpty()) {
                citiesList = parseCityListJson(citiesJson);
            }
        }
        if (!citiesList.isEmpty()){
            presenter.onSuccess(citiesList);
            return;
        }
        presenter.onFail();
    }

    private ArrayList<Cities> parseCityListJson(String citiesJson) {
        ArrayList<Cities> cityList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(citiesJson);
            for(int idx = 0 ; idx <jsonArray.length() ; idx++) {
                JSONObject jsonObject = jsonArray.getJSONObject(idx);
                Cities city = new Cities();
                city.setName(jsonObject.getString("name"));
                city.setCountry(jsonObject.getString("country"));
                city.setId(jsonObject.getInt("_id"));
                JSONObject coordinatesJsonObject = jsonObject.getJSONObject("coord");
                city.setLatitude(coordinatesJsonObject.getDouble("lat"));
                city.setLongitude(coordinatesJsonObject.getDouble("lon"));
                cityList.add(city);
            }
            Collections.sort(cityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    private String getCityListInfoFromAssets() {

        if(context.get() != null){
            try{
                AssetManager manager = context.get().getAssets();
                InputStream file = manager.open(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        return null;
    }

}
