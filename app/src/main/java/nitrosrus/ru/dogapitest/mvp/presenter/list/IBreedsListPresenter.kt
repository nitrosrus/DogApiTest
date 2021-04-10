package nitrosrus.ru.dogapitest.mvp.presenter.list

import nitrosrus.ru.dogapitest.mvp.view.list.BreedsItemView


interface IBreedsListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: BreedsItemView)
    fun getChar(adapterPosition: Int): String?
}