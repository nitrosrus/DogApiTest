package com.example.dogapitest.mvp.presenter.list

import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.mvp.view.list.SubBreedsItemView

interface ISubBreedListPresenter {
    var itemClickListener: ((SubBreedsItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: SubBreedsItemView)
}