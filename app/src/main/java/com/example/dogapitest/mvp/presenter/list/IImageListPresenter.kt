package com.example.dogapitest.mvp.presenter.list


import com.example.dogapitest.mvp.view.list.ImageItemView

interface IImageListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: ImageItemView)

}