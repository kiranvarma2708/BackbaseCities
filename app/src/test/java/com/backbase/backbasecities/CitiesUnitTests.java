package com.backbase.backbasecities;

import com.backbase.backbasecities.models.Cities;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by KiranKumar on 04/08/20.
 */
public class CitiesUnitTests {

    private ArrayList<Cities> input = new ArrayList<>();
    private ArrayList<Cities> output = new ArrayList<>();
    private ArrayList<Cities> result = new ArrayList<>();

    @Before
    public void setUp() {

        input.add(new Cities("Alabama", "US"));
        input.add(new Cities("Albuquerque", "US"));
        input.add(new Cities("Anaheim", "US"));
        input.add(new Cities("Arizona", "US"));
        input.add(new Cities("Schaumburg", "US"));

        output.add(new Cities("Alabama", "US"));
        output.add(new Cities("Albuquerque", "US"));
        output.add(new Cities("Anaheim", "US"));
        output.add(new Cities("Arizona", "US"));
    }


    @Test
    public void queryByCharacterA() {

        // Providing the character "A" should yield the List values which starts with A, removing Sydney from the input.
        result = Utilities.citySearchbyQuery(input, "a");

        assertNotNull(result);
        assertEquals(result.size(), 4);

        assertEquals(output.iterator().next().getName(), result.iterator().next().getName());
    }


    @Test
    public void queryByCharacterS() {

        //Providing the query as "S", the output should be "Sydney, AU" value as provided in the input list
        result = Utilities.citySearchbyQuery(input, "S");

        assertNotNull(result);
        assertEquals(result.size(), 1);

        assertEquals(result.iterator().next().getName(), "Schaumburg");
    }


    @Test
    public void searchByPrefixInvalid() {
        // Providing the special character to test the invalid query
        result = Utilities.citySearchbyQuery(input, "?");

        assertEquals(0, result.size());
    }

    @Test
    public void searchByPrefixNull() {

        // Providing the null to test the validity of the result.
        result = Utilities.citySearchbyQuery(input, null);

        assertEquals(0, result.size());

        // Providing the null should yield no values.
        result = Utilities.citySearchbyQuery(null, null);

        assertEquals(0, result.size());
    }

}