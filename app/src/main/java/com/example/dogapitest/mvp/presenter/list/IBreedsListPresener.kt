package com.example.dogapitest.mvp.presenter.list

import com.example.dogapitest.mvp.view.list.BreedsItemView

interface IBreedsListPresener {
    var itemClickListener: ((BreedsItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: BreedsItemView)
}