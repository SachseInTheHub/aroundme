package com.nelsonsachse.aroundme.api.data;

import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("rating")
    double rating;

    public String getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }
}
