package nitrosrus.ru.dogapitest.mvp.model.image


import nitrosrus.ru.dogapitest.mvp.model.cache.IImageCache

interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(url: String, container: T,preload: T)

}