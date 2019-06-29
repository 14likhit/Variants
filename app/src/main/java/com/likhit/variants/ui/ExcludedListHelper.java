package com.likhit.variants.ui;

import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.data.models.ExcludeItem;
import com.likhit.variants.data.models.ExcludeListHelperModel;
import com.likhit.variants.data.models.Variation;

import java.util.ArrayList;
import java.util.List;

public class ExcludedListHelper {

    private static List<List<ExcludeItem>> excludedList;
    private static List<ExcludeListHelperModel> excludedListHelpers = new ArrayList<>();

    public static void setExcludedList(List<List<ExcludeItem>> excludedList) {
        ExcludedListHelper.excludedList = excludedList;
    }

    public static void setExcludingMapping() {
        for (int i = 0; i < excludedList.size(); i++) {
            ExcludeListHelperModel excludedListHelper = new ExcludeListHelperModel();
            excludedListHelper.setFromGroupId(excludedList.get(i).get(0).getGroupId());
            excludedListHelper.setFromVariationId(excludedList.get(i).get(0).getVariationId());
            excludedListHelper.setToGroupId(excludedList.get(i).get(1).getGroupId());
            excludedListHelper.setToVariationId(excludedList.get(i).get(1).getVariationId());
            excludedListHelpers.add(excludedListHelper);
            excludedListHelpers.add(new ExcludeListHelperModel(excludedListHelper));
        }
    }

    public static List<Variation> getUpdatedVariationsList(String fromGroupId, String fromVariationId, String toGroupId, List<Variation> variations) {

        List<String> variationIds = new ArrayList<>();
        List<Variation> variationList = new ArrayList<>();
        for (ExcludeListHelperModel excludeListHelperModel : excludedListHelpers) {
            if (excludeListHelperModel.getFromGroupId().equalsIgnoreCase(fromGroupId) &&
                    excludeListHelperModel.getFromVariationId().equalsIgnoreCase(fromVariationId) &&
                    excludeListHelperModel.getToGroupId().equalsIgnoreCase(toGroupId)) {
                variationIds.add(excludeListHelperModel.getToVariationId());
            }
        }

        for (Variation variation : variations) {
            if (!variationIds.contains(variation.getId())) {
                variationList.add(variation);
            }
        }

        return variationList;

    }


}
