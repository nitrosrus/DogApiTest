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
                R.id.top_action_share -> sharePhotoClick()

            }
            true
        }

//        customActionBarBinding.ivActionBack.setOnClickListener { onBackPressed() }
//        customActionBarBinding.ivActionShare.setOnClickListener { sharePhotoClick() }
//        iv_action_share.setOnClickListener { sharePhotoClick() }
//       tv_action_back.setOnClickListener { onBackPressed() }
    }

    private fun sharePhotoClick() {
        //SharePhoto.newInstance().show(supportFragmentManager, MAIN_ACTIVITY)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backClicked()) {
                return
            }
        }
        presenter.backClicked()
    }

    override fun setActionBarTitle(text: String) {
        binding.topBar.title = text
//        customActionBarBinding.tvActionTitle.text=text

    }

    override fun setBreedScreensSetting(text: String) {
//        customActionBarBinding.ivActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionShare.visibility = View.INVISIBLE
//        customActionBarBinding.tvActionBack.text = customActionBarBinding.tvActionTitle.text
//        customActionBarBinding.tvActionBack.visibility = View.VISIBLE
        binding.topBar.title = text
        // bab_nav.visibility = View.GONE
    }

    override fun setImageBreedScreenSetting(text: String) {
//        customActionBarBinding.tvActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionShare.visibility = View.VISIBLE
//        customActionBarBinding.tvActionBack.text = customActionBarBinding.tvActionTitle.text
        binding.topBar.title = text
        //bab_nav.visibility = View.GONE
    }

    override fun setImageBreedScreenSetting(breed: String, subBreed: String) {
//        customActionBarBinding.tvActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionShare.visibility = View.VISIBLE
//        customActionBarBinding.tvActionBack.text = customActionBarBinding.tvActionTitle.text
//        customActionBarBinding.tvActionTitle.text = subBreed
        //bab_nav.visibility = View.GONE
    }

    override fun setFirstScreenSetting(firstScreen: Int) {

//        customActionBarBinding.tvActionBack.visibility = View.INVISIBLE
//        customActionBarBinding.ivActionBack.visibility = View.INVISIBLE
//        customActionBarBinding.ivActionShare.visibility = View.INVISIBLE
        binding.topBar.title = getString(firstScreen)
        //bab_nav.visibility = View.VISIBLE

    }

    override fun setSubBreedScreenSetting(breed: String) {
//        customActionBarBinding.tvActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionBack.visibility = View.VISIBLE
//        customActionBarBinding.ivActionShare.visibility = View.INVISIBLE
//        customActionBarBinding.tvActionBack.text = customActionBarBinding.tvActionTitle.text
        binding.topBar.title = breed
//        customActionBarBinding.tvActionBack.text = "Back"
        //bab_nav.visibility = View.GONE
    }

    override fun setLikeBreedScreenSetting() {
//        customActionBarBinding.tvActionBack.visibility = View.INVISIBLE
//        customActionBarBinding.ivActionBack.visibility = View.INVISIBLE
//        customActionBarBinding.ivActionShare.visibility = View.INVISIBLE
        //bab_nav.visibility = View.VISIBLE
    }

    override fun setFavouritesImageScreenSetting(breed: String) {
//      customActionBarBinding.tvActionBack.visibility = View.VISIBLE
//      customActionBarBinding.ivActionBack.visibility = View.VISIBLE
//      customActionBarBinding.ivActionShare.visibility = View.VISIBLE
//      customActionBarBinding.tvActionBack.text = customActionBarBinding.tvActionTitle.text
//      customActionBarBinding.tvActionTitle.text = breed
        //bab_nav.visibility = View.GONE
    }
}