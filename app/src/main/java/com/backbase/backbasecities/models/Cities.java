package com.backbase.backbasecities.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Cities implements Parcelable, Comparable<Cities> {

    private String name;
    private String country;

    @SerializedName("_id")
    private Integer id;
    @SerializedName("coord")
    private double latitude;
    private double longitude;

    public Cities(){ }

    public Cities(String name, String country){
        this.name = name;
        this.country = country;
    }

    private Cities(Parcel in) {
        name = in.readString();
        country = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Cities> CREATOR = new Creator<Cities>() {
        @Override
        public Cities createFromParcel(Parcel in) {
            return new Cities(in);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(country);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }

        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @Override
    public int compareTo(Cities o) {
        if(name.equals(o.name)){
            return  country.compareToIgnoreCase(o.country);
        }
        return name.compareToIgnoreCase(o.name);
    }
}
