package com.example.dogapitest.navigation


import com.example.dogapitest.ui.fragment.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class BreedsScreen() : SupportAppScreen() {
        override fun getFragment() = BreedsFragment.newInstance()
    }

    class SubBreadsScreen(val breed: ArrayList<String>) : SupportAppScreen() {
        override fun getFragment() = SubBreedsFragment.newInstance(breed)
    }

    class ImageScreen(val list: ArrayList<String>) : SupportAppScreen() {
        override fun getFragment() = ImageFragment.newInstance(list)
    }

    class LikeBreedsScreen() : SupportAppScreen() {
        override fun getFragment() = LikeBreedsFragment.newInstance()
    }
    class LikeImageScreen(val breedName: String) : SupportAppScreen() {
        override fun getFragment() = LikeImageFragment.newInstance(breedName)
    }
}