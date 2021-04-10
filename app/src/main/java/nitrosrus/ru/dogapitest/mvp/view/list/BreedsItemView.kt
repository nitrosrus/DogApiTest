package nitrosrus.ru.dogapitest.mvp.view.list

interface BreedsItemView {
    var pos: Int
    fun setBreed(breed: String)
    fun setCountBreed(countBreed: String)
    fun setClickListener()
    fun setCountVisible()
    fun setCountInvisible()

}