package com.example.dogapitest.mvp.presenter.list

import com.example.dogapitest.mvp.view.list.SubBreedsItemView

interface ISubBreedListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: SubBreedsItemView)
}