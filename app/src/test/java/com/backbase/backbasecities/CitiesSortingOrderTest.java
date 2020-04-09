package com.backbase.backbasecities;

import com.backbase.backbasecities.models.Cities;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CitiesSortingOrderTest {

    private Utilities citiesSortOrder = new Utilities();
    private List<Cities> input = new ArrayList<>();
    private List<Cities> output = new ArrayList<>();

    @Before
    public void setUp() {

        input.add(new Cities("Alabama", "RT"));
        input.add(new Cities("Denver", "FG"));
        input.add(new Cities("Alabama", "US"));
        input.add(new Cities("Sydney", "AU"));
        input.add(new Cities("Denver", "ZH"));
        input.add(new Cities("Arizona", "US"));
        input.add(new Cities("Albuquerque", "US"));
        input.add(new Cities("Anaheim", "US"));

        output.add(new Cities("Alabama", "RT"));
        output.add(new Cities("Alabama", "US"));
        output.add(new Cities("Albuquerque", "US"));
        output.add(new Cities("Anaheim", "US"));
        output.add(new Cities("Arizona", "US"));
        output.add(new Cities("Denver", "FG"));
        output.add(new Cities("Denver", "ZH"));
        output.add(new Cities("Sydney", "AU"));

    }

    @Test
    public void sortTheCities() {

        // Providing the input list to the method yields the sorted list in Alphabetical order
        List<Cities> sorted = Utilities.citiesSortBy(input);

        assertEquals(sorted.iterator().next().getName(), output.iterator().next().getName()); // Comparing the getName value of both lists.

    }

    @Test
    public void compareWithoutAnySorting() {
        assertNotEquals(output, input);
    }

}