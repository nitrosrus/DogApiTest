package nitrosrus.ru.dogapitest.mvp.presenter.list

import nitrosrus.ru.dogapitest.mvp.view.list.SubBreedsItemView

interface ISubBreedListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: SubBreedsItemView)
}