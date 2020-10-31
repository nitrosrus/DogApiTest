package com.example.dogapitest.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.MainPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.MainView
import com.example.dogapitest.ui.network.SharePhoto
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView, DpVisible {


    companion object {
        const val MAIN_ACTIVITY = "MainActivity"

    }

    lateinit var bottomBar: BottomNavigationView

    override fun setActionTitle(text: String) {
        tv_action_title.text = text
    }

    override fun setBreedScreensSetting(text: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = text
        bab_nav.visibility = View.GONE
    }

    override fun setImageBreedScreenSetting(text: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.VISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = text
        bab_nav.visibility = View.GONE
    }

    override fun setImageBreedScreenSetting(breed: String, subBreed: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.VISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = subBreed
        bab_nav.visibility = View.GONE
    }

    override fun setFirstScreenSetting(breed: String) {
        tv_action_back.visibility = View.INVISIBLE
        iv_action_back.visibility = View.INVISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_title.text = breed
        bab_nav.visibility = View.VISIBLE

    }

    override fun setSubBreedScreenSetting(breed: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = breed
        tv_action_back.text = "Back"
        bab_nav.visibility = View.GONE
    }

    override fun setLikeBreedScreenSetting() {
        tv_action_back.visibility = View.INVISIBLE
        iv_action_back.visibility = View.INVISIBLE
        iv_action_share.visibility = View.INVISIBLE
        bab_nav.visibility = View.VISIBLE
    }

    override fun setLikeImageScreenSetting(breed: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.VISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = breed
        bab_nav.visibility = View.GONE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
        supportActionBar?.apply {
            setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.custom_action_bar)
        }
        bottomBar = findViewById(R.id.bab_nav)
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_list -> { presenter.btnList()
                }
                R.id.btn_favorites -> {presenter.btnFavorites()
                }
            }
            true
        }

        iv_action_share.setOnClickListener { test() }
        tv_action_back.setOnClickListener { onBackPressed() }


    }


    val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun init() {

    }

    fun test() {
        SharePhoto.newInstance().show(supportFragmentManager, MAIN_ACTIVITY)
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

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backClicked()) {
                return
            }
        }
        presenter.backClicked()
    }


}