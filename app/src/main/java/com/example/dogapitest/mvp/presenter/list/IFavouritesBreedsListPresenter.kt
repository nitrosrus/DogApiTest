package com.example.dogapitest.mvp.presenter.list


import com.example.dogapitest.mvp.view.list.FavouritesBreedsItemView


interface IFavouritesBreedsListPresenter {
    var itemClick: ((String) -> Unit)?
    var itemClickListener: ((FavouritesBreedsItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: FavouritesBreedsItemView)
}