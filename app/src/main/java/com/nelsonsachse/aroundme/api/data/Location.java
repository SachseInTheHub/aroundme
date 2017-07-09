package com.nelsonsachse.aroundme.api.data;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
    String address;

    @SerializedName("postalCode")
    String postalCode;

    @SerializedName("distance")
    String distance;

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
