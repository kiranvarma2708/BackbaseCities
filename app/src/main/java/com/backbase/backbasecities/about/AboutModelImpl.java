package com.backbase.backbasecities.about;

import android.content.Context;
import android.util.Log;
import java.lang.ref.WeakReference;
import androidx.annotation.NonNull;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutModelImpl implements About.Model {

    private static final String TAG = AboutModelImpl.class.getSimpleName();
    private final About.Presenter presenter;

    public AboutModelImpl(@NonNull About.Presenter presenter, @NonNull Context context){
        this.presenter = presenter;
        WeakReference<Context> context1 = new WeakReference<>(context);
    }

    @Override
    public void getAboutInfo(String name, String country, String coord, int id) {
        AboutInfo aboutInfo = parseAboutInfo(name, country, coord, id);
        if (name != null) {
            presenter.onSuccess(aboutInfo);
            return;
        }
        presenter.onFail();
    }

    private AboutInfo parseAboutInfo(String name, String country, String coord, int id) {
        AboutInfo aboutInfo = null;
        try {
            aboutInfo = new AboutInfo();
            aboutInfo.setName(name);
            aboutInfo.setCountry(country);
            aboutInfo.setCoord(coord);
            aboutInfo.setId(id);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return aboutInfo;
    }

}
