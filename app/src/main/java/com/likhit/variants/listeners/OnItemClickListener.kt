package com.likhit.variants.listeners

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int, view: View)
}
