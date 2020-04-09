package com.backbase.backbasecities.about;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * DTO representing aboutInfo object
 */

public class AboutInfo {

    private String name;
    private String country;
    private String coord;
    private int id;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCoord() { return coord; }

    public void setCoord(String coord) { this.coord = coord; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}
