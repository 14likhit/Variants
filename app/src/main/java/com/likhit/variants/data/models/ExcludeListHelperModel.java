package com.likhit.variants.data.models;

public class ExcludeListHelperModel {

    private String fromGroupId;
    private String fromVariationId;
    private String toGroupId;
    private String toVariationId;

    public ExcludeListHelperModel() {
    }

    public ExcludeListHelperModel(ExcludeListHelperModel excludeListHelperModel) {
        this.fromGroupId = excludeListHelperModel.getToGroupId();
        this.fromVariationId = excludeListHelperModel.getToVariationId();
        this.toGroupId = excludeListHelperModel.getFromGroupId();
        this.toVariationId = excludeListHelperModel.getFromVariationId();
    }

    public String getFromGroupId() {
        return fromGroupId;
    }

    public void setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
    }

    public String getFromVariationId() {
        return fromVariationId;
    }

    public void setFromVariationId(String fromVariationId) {
        this.fromVariationId = fromVariationId;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getToVariationId() {
        return toVariationId;
    }

    public void setToVariationId(String toVariationId) {
        this.toVariationId = toVariationId;
    }
}
