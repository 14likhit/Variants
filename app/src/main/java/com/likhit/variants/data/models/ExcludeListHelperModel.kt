package com.likhit.variants.data.models

class ExcludeListHelperModel {

    var fromGroupId: String? = null
    var fromVariationId: String? = null
    var toGroupId: String? = null
    var toVariationId: String? = null

    constructor() {}

    constructor(excludeListHelperModel: ExcludeListHelperModel) {
        this.fromGroupId = excludeListHelperModel.toGroupId
        this.fromVariationId = excludeListHelperModel.toVariationId
        this.toGroupId = excludeListHelperModel.fromGroupId
        this.toVariationId = excludeListHelperModel.fromVariationId
    }
}
