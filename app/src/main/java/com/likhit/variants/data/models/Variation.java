package com.likhit.variants.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Variation implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("default")
    private Integer defaultz;

    @SerializedName("id")
    private String id;

    @SerializedName("logo")
    private String logo;

    public Variation(String name, Integer defaultz, String id, String logo) {
        this.name = name;
        this.defaultz = defaultz;
        this.id = id;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public Integer getDefaultz() {
        return defaultz;
    }

    public String getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }
}
