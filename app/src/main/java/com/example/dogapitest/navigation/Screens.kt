package com.example.dogapitest.navigation


import com.example.dogapitest.ui.fragment.*
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


    class LikeBreedsScreen() : SupportAppScreen() {
        override fun getFragment() = LikeBreedsFragment.newInstance()
    }

    class LikeImageScreen(val breedName: String) : SupportAppScreen() {
        override fun getFragment() = LikeImageFragment.newInstance(breedName)
    }
}