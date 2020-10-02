package com.example.dogapitest.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.core.graphics.ColorUtils
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.MainPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView, DpVisible {


    override fun setActionTitle(text: String) {
        tv_action_title.text = text
    }

    override fun setBreedScreensSetting(text: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = text
        ll_bottom.visibility = View.GONE
    }

    override fun setImageBreedScreenSetting(text: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.VISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = text
        ll_bottom.visibility = View.GONE
    }

    override fun setImageBreedScreenSetting(breed: String, subBreed: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.VISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = subBreed
        ll_bottom.visibility = View.GONE
    }

    override fun setFerstScreenSetting(breed: String) {
        tv_action_back.visibility = View.INVISIBLE
        iv_action_back.visibility = View.INVISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_title.text = breed
        ll_bottom.visibility = View.VISIBLE
        btn_list.setColorFilter(Color.parseColor("#039BE5"))
        tv_btn_list.setTextColor(Color.parseColor("#039BE5"))
        btn_favorites.setColorFilter(Color.parseColor("#FF000000"))
        tv_btn_favorites.setTextColor(Color.parseColor("#FF000000"))
    }

    override fun setSubBreedScreenSetting(breed: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = breed
        tv_action_back.text = "Back"
        ll_bottom.visibility = View.GONE
    }

    override fun setLikeBreedScreenSetting() {
        tv_action_back.visibility = View.INVISIBLE
        iv_action_back.visibility = View.INVISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_title.text = tv_btn_favorites.text
        ll_bottom.visibility = View.VISIBLE
        btn_list.setColorFilter(Color.parseColor("#FF000000"))
        tv_btn_list.setTextColor(Color.parseColor("#FF000000"))
        btn_favorites.setColorFilter(Color.parseColor("#039BE5"))
        tv_btn_favorites.setTextColor(Color.parseColor("#039BE5"))
    }

    override fun setLikeImageScreenSetting(breed: String) {
        tv_action_back.visibility = View.VISIBLE
        iv_action_back.visibility = View.VISIBLE
        iv_action_share.visibility = View.VISIBLE
        tv_action_back.text = tv_action_title.text
        tv_action_title.text = breed
        ll_bottom.visibility = View.GONE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
        btn_list.setOnClickListener { presenter.btnList() }
        btn_favorites.setOnClickListener { presenter.btnFavorites() }

        supportActionBar?.apply {
            setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.custom_action_bar)
            elevation
        }

        tv_action_back.setOnClickListener { onBackPressed() }
        iv_action_share.setOnClickListener { }
    }

    val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun init() {

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