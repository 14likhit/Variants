package com.likhit.variants.ui.helper

import com.likhit.variants.data.models.ExcludeItem
import com.likhit.variants.data.models.ExcludeListHelperModel
import com.likhit.variants.data.models.Variation

import java.util.ArrayList

object ExcludedListHelper {

    private var excludedList: List<List<ExcludeItem>>? = null
    private val excludedListHelpers = ArrayList<ExcludeListHelperModel>()

    fun setExcludedList(excludedList: List<List<ExcludeItem>>) {
        ExcludedListHelper.excludedList = excludedList
    }

    fun setExcludingMapping() {
        for (i in excludedList!!.indices) {
            val excludedListHelper = ExcludeListHelperModel()
            excludedListHelper.fromGroupId = excludedList!![i][0].groupId
            excludedListHelper.fromVariationId = excludedList!![i][0].variationId
            excludedListHelper.toGroupId = excludedList!![i][1].groupId
            excludedListHelper.toVariationId = excludedList!![i][1].variationId
            excludedListHelpers.add(excludedListHelper)
            excludedListHelpers.add(ExcludeListHelperModel(excludedListHelper))
        }
    }

    fun getUpdatedVariationsList(fromGroupId: String, fromVariationId: String, toGroupId: String, variations: List<Variation>): List<Variation> {

        val variationIds = ArrayList<String>()
        val variationList = ArrayList<Variation>()
        for (excludeListHelperModel in excludedListHelpers) {
            if (excludeListHelperModel.fromGroupId!!.equals(fromGroupId, ignoreCase = true) &&
                    excludeListHelperModel.fromVariationId!!.equals(fromVariationId, ignoreCase = true) &&
                    excludeListHelperModel.toGroupId!!.equals(toGroupId, ignoreCase = true)) {
                variationIds.add(excludeListHelperModel.toVariationId!!)
            }
        }

        for (variation in variations) {
            if (!variationIds.contains(variation.id)) {
                variationList.add(variation)
            }
        }

        return variationList

    }


}
