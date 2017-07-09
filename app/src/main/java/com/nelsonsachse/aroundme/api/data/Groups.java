package com.nelsonsachse.aroundme.api.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Groups {

    @SerializedName("items")
    List<Items> items;

    public List<Items> getItems() {
        return items;
    }
}