package com.example.dogapitest.mvp.presenter.list


import com.example.dogapitest.mvp.view.list.FavouritesBreedsItemView


interface IFavouritesBreedsListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: FavouritesBreedsItemView)
}