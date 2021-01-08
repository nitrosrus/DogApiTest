package com.example.dogapitest.mvp.presenter.list

import com.example.dogapitest.mvp.view.list.BreedsItemView



interface IBreedsListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: BreedsItemView)
}