package com.example.dogapitest.mvp.presenter.list


import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.mvp.view.list.FavouritesBreedsItemView
import com.example.dogapitest.mvp.view.list.LikeImageItemView
import com.example.dogapitest.ui.adapter.SubBreedsRVAdapter


interface IFavouritesBreedsListPresenter {
    var itemClick: ((String) -> Unit)?
    var itemClickListener: ((FavouritesBreedsItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: FavouritesBreedsItemView)
}