package com.likhit.variants.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Variants implements Serializable {

    @SerializedName("variant_groups")
    private List<VariantGroup> variantGroups;

    @SerializedName("exclude_list")
    private List<List<ExcludeItem>> excludeList;

    public Variants(Variants variants) {
        this.variantGroups = variants.getVariantGroups();
        this.excludeList = variants.getExcludeList();
    }

    public Variants(List<VariantGroup> variantGroups) {
        this.variantGroups = variantGroups;
    }

    public Variants(List<VariantGroup> variantGroups, List<List<ExcludeItem>> excludeList) {
        this.variantGroups = variantGroups;
        this.excludeList = excludeList;
    }

    public List<VariantGroup> getVariantGroups() {
        return variantGroups;
    }

    public void setVariantGroups(List<VariantGroup> variantGroups) {
        this.variantGroups = variantGroups;
    }

    public List<List<ExcludeItem>> getExcludeList() {
        return excludeList;
    }
}
