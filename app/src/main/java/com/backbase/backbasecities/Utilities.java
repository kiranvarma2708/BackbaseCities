package com.backbase.backbasecities;

import com.backbase.backbasecities.models.Cities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Utilities {

    static ArrayList<Cities> citySearchbyQuery(ArrayList<Cities> entries, String prefix) {
        final ArrayList<Cities> entriesWithPrefix = new ArrayList<>();
        if (entries == null || prefix == null) {
            return entriesWithPrefix;
        }
        for (final Cities entry : entries) {
            if (entry.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                entriesWithPrefix.add(entry);
            }
        }
        return entriesWithPrefix;
    }

    static List<Cities> citiesSortBy(List<Cities> entries) {
        Collections.sort(entries, (entry1, entry2) -> {
            String city1 = entry1.getName();
            String city2 = entry2.getName();
            int i = city1.compareToIgnoreCase(city2);
            if (i != 0) return i;

            String country1 = entry1.getCountry();
            String country2 = entry2.getCountry();
            i = country1.compareToIgnoreCase(country2);

            return i;
        });
        return entries;
    }
}
