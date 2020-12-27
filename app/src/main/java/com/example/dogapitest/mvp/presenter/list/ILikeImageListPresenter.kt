package com.example.dogapitest.mvp.presenter.list


import com.example.dogapitest.mvp.view.list.ImageItemView
import com.example.dogapitest.mvp.view.list.LikeImageItemView

interface ILikeImageListPresenter {
    var itemClickListener: ((LikeImageItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: LikeImageItemView)
    fun likeBTN(view: LikeImageItemView)

}