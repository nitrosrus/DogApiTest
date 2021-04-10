package nitrosrus.ru.dogapitest.navigation


import nitrosrus.ru.dogapitest.ui.fragment.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class BreedsScreen : SupportAppScreen() {
        override fun getFragment() = BreedsFragment.newInstance()
    }

    class SubBreadsScreen(val breed: String) : SupportAppScreen() {
        override fun getFragment() = SubBreedsFragment.newInstance(breed)
    }

    class ImageScreen(val breed: String, val subBreed: String?) : SupportAppScreen() {
        override fun getFragment() = ImageFragment.newInstance(breed, subBreed)
    }


    class FavouritesBreedsScreen() : SupportAppScreen() {
        override fun getFragment() = FavouritesBreedsFragment.newInstance()
    }

    class LikeImageScreen(val breedName: String) : SupportAppScreen() {
        override fun getFragment() = FavouritesImageFragment.newInstance(breedName)
    }
}