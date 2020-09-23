package com.example.dogapitest.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.MainPresenter
import com.example.dogapitest.mvp.view.MainView
import com.jakewharton.rxbinding3.view.visibility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.item_breeds.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
        btn_list.setOnClickListener { presenter.btnList() }
        btn_favorites.setOnClickListener { presenter.btnFavorites() }
        val actionbar = supportActionBar
        actionbar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        actionbar.setDisplayShowCustomEnabled(true)
        actionbar.setCustomView(R.layout.custom_action_bar)
        actionbar.elevation


    }



    fun invisible() {
//        if (iv_action_back.visibility == 0) iv_action_back.visibility =
//            View.INVISIBLE else iv_action_back.visibility = View.VISIBLE
    }


    @ProvidePresenter
    fun providePresenter() = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }


    override fun init() {
        tv_action_back.visibility = View.INVISIBLE
        iv_action_back.visibility = View.INVISIBLE
        iv_action_share.visibility = View.INVISIBLE
        tv_action_title.text = "Breeds" //знаю что хардкод)


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