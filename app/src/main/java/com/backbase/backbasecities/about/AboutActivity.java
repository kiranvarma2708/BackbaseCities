package com.backbase.backbasecities.about;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.backbase.backbasecities.R;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity implements About.View {

    private TextView nameTextView;
    private TextView countryTextView;
    private TextView coordTextView;
    private TextView idTextView;
    private ProgressBar progressBar;
    private android.view.View errorView;
    private android.view.View infoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Intent intent = getIntent();
        AboutPresenterImpl aboutPresenter = new AboutPresenterImpl(this, this);

        nameTextView = findViewById(R.id.info_name_textView);
        countryTextView = findViewById(R.id.info_country);
        coordTextView = findViewById(R.id.info_location);
        idTextView = findViewById(R.id.info_id);
        progressBar = findViewById(R.id.progressBar);
        errorView = findViewById(R.id.errorView);
        infoContainer = findViewById(R.id.infoContainer);

        aboutPresenter.getAboutInfo(intent.getStringExtra("name"), intent.getStringExtra("country"), intent.getStringExtra("coord"), intent.getIntExtra("id", 0));

        findViewById(R.id.close_button).setOnClickListener(v -> finish());
    }

    @Override
    public void setName(String nameString) {
        infoContainer.setVisibility(android.view.View.VISIBLE);
        nameTextView.setText(nameString);
    }

    @Override
    public void setCountry(String countryString) {
        countryTextView.setText(countryString);
    }

    @Override
    public void setCoord(String coordString) {
        coordTextView.setText(coordString);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setId(int idInt) {
        idTextView.setText(idInt+"");
    }

    @Override
    public void showError() {
        errorView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }
}
