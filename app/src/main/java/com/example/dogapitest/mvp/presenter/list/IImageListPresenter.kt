package com.example.dogapitest.mvp.presenter.list

import android.view.View
import com.example.dogapitest.mvp.view.list.ImageItemView

interface IImageListPresenter {
    var itemClickListener: ((ImageItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: ImageItemView)
    fun likeBTN(view: ImageItemView)

}