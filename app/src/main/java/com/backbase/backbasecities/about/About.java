package com.backbase.backbasecities.about;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * MVP contract for AboutActivity
 */

public interface About {

    interface Model {
        void getAboutInfo(String city, String country, String coord, int id);
    }

    interface Presenter {
        void getAboutInfo(String city, String country, String coord, int id);
        void onSuccess(AboutInfo aboutInfo);
        void onFail();
    }

    interface View {
        void setName(String name);
        void setCountry(String country);
        void setCoord(String coord);
        void setId(int id);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
