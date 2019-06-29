package com.likhit.variants.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VariantGroup implements Serializable {

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("name")
    private String name;

    @SerializedName("variations")
    private List<Variation> variations;

    public VariantGroup(String groupId, String name, List<Variation> variations) {
        this.groupId = groupId;
        this.name = name;
        this.variations = variations;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<Variation> getVariations() {
        return variations;
    }

    public void setVariations(List<Variation> variations) {
        this.variations = variations;
    }
}
