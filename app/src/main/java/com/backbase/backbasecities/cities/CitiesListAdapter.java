package com.backbase.backbasecities.cities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.backbase.backbasecities.R;
import com.backbase.backbasecities.models.Cities;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CityViewHolder> implements Filterable {

    private ArrayList<Cities> cityList;
    private ArrayList<Cities> cityListFiltered;
    private OnCitiesListClickListener onCitiesListClickListener;
    private RecyclerView recyclerView;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (cityList == null) {
                    return filterResults;
                }
                String query = charSequence.toString();
                if (query.isEmpty()) {
                    cityListFiltered = cityList;
                } else {
                    ArrayList<Cities> filteredList = new ArrayList<>();
                    for (Cities city : cityList) {
                        if (city.getName().toLowerCase().startsWith(query.toLowerCase())) {
                            filteredList.add(city);
                        }
                    }
                    cityListFiltered = filteredList;
                }

                filterResults.count = cityListFiltered.size();
                filterResults.values = cityListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cityListFiltered = (ArrayList<Cities>) filterResults.values;
                recyclerView.getRecycledViewPool().clear();
                notifyDataSetChanged();
            }
        };
    }

    CitiesListAdapter(OnCitiesListClickListener onCitiesListClickListener) {
        this.onCitiesListClickListener = onCitiesListClickListener;
    }


    public void updateCityList(ArrayList<Cities> cityList) {
        this.cityList = cityList;
        this.cityListFiltered = cityList;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_item, parent, false);
        return new CityViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CitiesListAdapter.CityViewHolder holder, int position) {
        if (cityListFiltered == null) {
            return;
        }
        final Cities filteredCity = cityListFiltered.get(position);
        holder.titleView.setText(filteredCity.getName() + ", " + filteredCity.getCountry());
        holder.subTitleView.setText("Location: " + filteredCity.getLatitude() + "," + filteredCity.getLongitude());
        holder.itemView.setOnClickListener(v -> onCitiesListClickListener.onCitiesClick(filteredCity));
    }

    @Override
    public int getItemCount() {
        return cityListFiltered == null ? 0 : cityListFiltered.size();
    }

    /**
     * Listener for Cities list click event
     */
    public interface OnCitiesListClickListener {
        void onCitiesClick(Cities cities);
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView subTitleView;
        CityViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            subTitleView = itemView.findViewById(R.id.subtitle);
        }
    }


}
