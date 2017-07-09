package com.nelsonsachse.aroundme.api.data;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("venue")
    Venue venue;

    public Venue getVenue() {
        return venue;
    }
}
