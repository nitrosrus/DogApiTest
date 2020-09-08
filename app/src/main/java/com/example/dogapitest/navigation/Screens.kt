package com.example.dogapitest.navigation



import com.example.dogapitest.ui.fragment.BreedsFragment
import com.example.dogapitest.ui.fragment.SubBreedsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class BreedsScreen() : SupportAppScreen() {
        override fun getFragment() = BreedsFragment.newInstance()
    }

    class SubBreadsScreen (val breeds:List<String>) : SupportAppScreen() {
        override fun getFragment() = SubBreedsFragment.newInstance(breeds)
    }
//
//    class RepositoryScreen(val repository: GithubRepository) : SupportAppScreen() {
//        override fun getFragment() = RepositoryFragment.newInstance(repository)
//    }

}