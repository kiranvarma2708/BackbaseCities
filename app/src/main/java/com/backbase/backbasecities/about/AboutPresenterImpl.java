package com.backbase.backbasecities.about;

import android.content.Context;
import android.os.Handler;
import java.lang.ref.WeakReference;
import androidx.annotation.NonNull;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutPresenterImpl implements About.Presenter {

    private final WeakReference<About.View> aboutView;
    private final AboutModelImpl aboutModel;

    public AboutPresenterImpl(About.View view, @NonNull Context context){
        this.aboutView = new WeakReference<>(view);
        this.aboutModel = new AboutModelImpl(this, context);
    }

    @Override
    public void getAboutInfo(String name, String country, String coord, int id) {
        About.View aboutViewImpl = aboutView.get();
        aboutViewImpl.showProgress();
        new Handler().postDelayed(() -> aboutModel.getAboutInfo(name, country, coord, id), 1000);
    }

    @Override
    public void onSuccess(AboutInfo aboutInfo) {
        About.View aboutViewImpl = aboutView.get();

        if ( aboutViewImpl != null ) {
            aboutViewImpl.hideProgress();
            aboutViewImpl.setName(aboutInfo.getName());
            aboutViewImpl.setCountry(aboutInfo.getCountry());
            aboutViewImpl.setCoord(aboutInfo.getCoord());
            aboutViewImpl.setId(aboutInfo.getId());
        }

    }

    @Override
    public void onFail() {
        About.View aboutViewImpl = aboutView.get();
        if (aboutViewImpl != null){
            aboutViewImpl.hideProgress();
            aboutViewImpl.showError();
        }
    }
}
