package nitrosrus.ru.dogapitest.mvp.view.list

interface ImageItemView {
    var pos: Int
    fun loadImage(url: String)
    fun setLikeEnable()
    fun setLikeDisable()
    fun setClickListener()
}