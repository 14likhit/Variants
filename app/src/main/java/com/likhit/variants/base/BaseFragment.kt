package com.likhit.variants.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast

abstract class BaseFragment : Fragment(), BaseView {

    protected val baseActivity: BaseActivity?
        get() = activity as BaseActivity?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    protected fun initViews(view: View) {

    }

    override fun showMessage(message: String) {
        if (baseActivity != null) {
            Toast.makeText(baseActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(messageResId: Int) {
        if (baseActivity != null) {
            Toast.makeText(baseActivity, baseActivity!!.getString(messageResId), Toast.LENGTH_SHORT).show()
        }
    }

    fun onBackPressed(): Boolean {
        return false
    }
}
