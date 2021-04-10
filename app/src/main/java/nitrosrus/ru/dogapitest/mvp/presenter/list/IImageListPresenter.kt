package nitrosrus.ru.dogapitest.mvp.presenter.list


import nitrosrus.ru.dogapitest.mvp.view.list.ImageItemView

interface IImageListPresenter {
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: ImageItemView)

}