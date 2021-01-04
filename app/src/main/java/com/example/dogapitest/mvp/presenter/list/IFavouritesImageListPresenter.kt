package com.example.dogapitest.mvp.presenter.list


import com.example.dogapitest.mvp.view.list.FavouritesImageItemView

interface IFavouritesImageListPresenter {
    var itemClickListener: ((view: FavouritesImageItemView) -> Unit)?
    fun getCount(): Int
    fun bind(view: FavouritesImageItemView)

}