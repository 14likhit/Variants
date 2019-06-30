package com.likhit.variants.base

import android.support.annotation.StringRes

interface BaseView {

    fun showMessage(message: String)

    fun showMessage(@StringRes messageResId: Int)

}
