package com.likhit.variants.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExcludeItem implements Serializable {

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("variation_id")
    private String variationId;

    public ExcludeItem(String groupId, String variationId) {
        this.groupId = groupId;
        this.variationId = variationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getVariationId() {
        return variationId;
    }
}
