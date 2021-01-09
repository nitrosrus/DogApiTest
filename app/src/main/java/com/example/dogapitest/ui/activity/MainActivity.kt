package com.example.dogapitest.ui.activity

import android.os.Bundle
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.databinding.ActivityMainBinding
import com.example.dogapitest.mvp.presenter.MainPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView, DpVisible {

    companion object {
        const val MAIN_ACTIVITY = "MainActivity"
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: MainPresenter

    lateinit var binding: ActivityMainBinding

    lateinit var bottomBar: BottomNavigationView

    lateinit var navigator: SupportAppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        App.instance.appComponent.inject(this)
    }

    @ProvidePresenter
    fun providePresenter() = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun init() {
        navigator = SupportAppNavigator(this, binding.container.id)
        bottomBar = binding.babNav
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_list -> {
                    presenter.btnList()
                }
                R.id.btn_favorites -> {
                    presenter.btnFavorites()
                }
            }
            true
        }
        binding.topBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                //R.id.top_action_share -> sharePhotoClick()
            }
            true
        }
        binding.topBar.setNavigationOnClickListener { presenter.backClicked() }

    }

    private fun sharePhotoClick() {
        //SharePhoto.newInstance().show(supportFragmentManager, MAIN_ACTIVITY)
    }

    override fun setBreedScreensSetting(breed: String) {
        binding.topBar.title = breed
    }

    override fun setImageBreedScreenSetting(breed: String) {
        binding.topBar.title = breed
    }

    override fun setFirstScreenSetting() {
        binding.topBar.title = getString(R.string.setFirstScreen)
    }

    override fun setSubBreedScreenSetting(breed: String) {
        binding.topBar.title = breed
    }

    override fun setLikeBreedScreenSetting() {
        binding.topBar.title = getString(R.string.setFirstScreen)
    }

    override fun setFavouritesImageScreenSetting(breed: String) {
        binding.topBar.title = breed
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backClicked()) {
                return
            }
        }
        presenter.backClicked()
    }
}