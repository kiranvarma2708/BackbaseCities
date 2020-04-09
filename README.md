# BackbaseCities
Backbase Cities Assessment

# Intro of the App
Integrating the cities.json into the android application and view it in the list. 
Can search for the cities from searchview. 
Implementing/Integrating unit test cases using JUnit and Espresso.

# Tasks
* Be able to filter the results by a given prefix string, following these requirements:
     * Follow the prefix definition specified in the clarifications section below.
     * Optimise for fast searches. Loading time of the app is not so important.
     * Search is case insensitive.
* Display these cities in a scrollable list, in alphabetical order (city first, country after). Hence, "Denver, US" should appear before "Sydney, Australia".
     * The UI should be as responsive as possible while typing in a filter.
     * The list should be updated with every character added/removed to/from the filter.
* Each city's cell should:
     * Show the city and country code as title.
     * Show the coordinates as subtitle.
     * When tapped, navigate the map to the coordinates of the city.
     * Contain a button that, when tapped, opens an information screen about the selected city. The code of this screen is available under the following folder[Android](androidTestClasses), and for iOS (ios-classes-to-unit-test).
* Create a dynamic UI that follows the [wireframe](wireframes). Hence, when in [portrait](wireframes/portrait.png) different screens should be used for the list and map but when in [landscape](wireframes/landscape.png), a single screen should be used.
* Provide unit tests showing that your search algorithm is displaying the correct results giving different inputs, including invalid inputs.
* Provide UI/unit tests for the information screen code we provided you. You are allowed to refactor if needed.


# Searching the cities
  Development:
* Implemented the searching of the cities from the json and add it to the recyclerview.
* Sorting the cities alphabetically as per the instructions, Used collections sort to sort all the items from the list.
* Sorted to identify and view the list as per the Clarifications mentioned.
* App takes some time to load as the reading and sorting is done at the time of starting the activity.
* Cities list will be updated by each character based on the sorted order.
* City, Country and Location details are showed in the individual item in the recyclerview.
* Navigating to the map fragment when tapped on the item.
* Integrated a button under the map which will display a nee screen to view the details of the selected city.
* Can come back to the map fragment and navigate back to the search/list.
* Implemented portrait and landscape mode to view different screen designs.

  Testing:
* Wrote JUnit unit test cases for validating the search process to make sure correct values are returning back.
* Implemented Espresso unit test cases to navigate to the About info screen from the Main screen which searches 
and navigate to Map fragment and to the about info screen and get the city name displayed and verified the 
name with the one we sent it int the test.

  Challenges:
* Implemeting the searching of the city from the json list.
* Sorting the cities list from the json file.

Thanks.
