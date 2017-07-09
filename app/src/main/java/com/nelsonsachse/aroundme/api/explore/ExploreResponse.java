package com.nelsonsachse.aroundme.api.explore;

import com.google.gson.annotations.SerializedName;

import com.nelsonsachse.aroundme.api.data.Groups;

import java.util.List;

public class ExploreResponse {

    @SerializedName("response")
    Response response;

    public Response getResponse() {
        return response;
    }

    public class Response {

        @SerializedName("groups")
        List<Groups> groups;

        public List<Groups> getGroups() {
            return groups;
        }
    }
}
