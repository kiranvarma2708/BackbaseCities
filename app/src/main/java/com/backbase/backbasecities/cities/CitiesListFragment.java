package com.backbase.backbasecities.cities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.backbase.backbasecities.R;
import com.backbase.backbasecities.models.Cities;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CitiesListFragment extends Fragment implements CitiesInterface.CitiesListView, CitiesListAdapter.OnCitiesListClickListener {

    private static final String SAVED_STRING ="saved";

    private CitiesListAdapter adapter;
    private TextView emptyView;
    private View progressView;
    private SearchView searchView;
    private ListenerOnCitiesSelected listenerOnCitiesSelected;
    private CitiesInterface.CitiesListPresenter citiesListPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cities_list_fragment, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        emptyView = rootView.findViewById(R.id.empty_view);
        progressView = rootView.findViewById(R.id.progress_view);
        searchView = rootView.findViewById(R.id.search_view);
        if (citiesListPresenter == null) {
            citiesListPresenter = new CitiesPresenterImpl(this, Objects.requireNonNull(getActivity()));
            citiesListPresenter.fetchCitiesList();
        }
        if (adapter == null) {
            adapter = new CitiesListAdapter( this);
        }
        if (savedInstanceState != null) {
            String query = savedInstanceState.getString(SAVED_STRING);
            searchView.setQuery(query, false);
        }
        searchView.clearFocus();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        setUpSearchView();

        return rootView;
    }

    private void setUpSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCitiesClick(Cities city) {
        listenerOnCitiesSelected.onSelectedCities(city);
    }

    @Override
    public void setCitiesList(ArrayList<Cities> cityList) {
        adapter.updateCityList(cityList);
    }

    @Override
    public void showError() {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(getString(R.string.string_exception));
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    public interface ListenerOnCitiesSelected {
        void onSelectedCities(Cities city);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_STRING, searchView.getQuery().toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listenerOnCitiesSelected = (ListenerOnCitiesSelected) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnCityClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
