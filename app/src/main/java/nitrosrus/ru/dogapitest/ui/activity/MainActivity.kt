package nitrosrus.ru.dogapitest.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import nitrosrus.ru.dogapitest.databinding.ActivityMainBinding
import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.BackButtonListener
import nitrosrus.ru.dogapitest.mvp.presenter.MainPresenter
import nitrosrus.ru.dogapitest.mvp.view.DpVisible
import nitrosrus.ru.dogapitest.mvp.view.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.mvp.presenter.BreedsPresenter
import nitrosrus.ru.dogapitest.mvp.presenter.FindTextListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView, DpVisible {


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
        App.instance.appComponent.inject(this)
        setContentView(binding.root)

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
        binding.ivBackBtn.setOnClickListener { presenter.backClicked() }

    }




    override fun setBreedScreensSetting(breed: String) {
        binding.tvTitle.text = breed
    }

    override fun setImageBreedScreenSetting(breed: String) {
        binding.tvTitle.text = breed
    }

    override fun setFirstScreenSetting() {
        binding.tvTitle.text = getString(R.string.setFirstScreen)
    }

    override fun setSubBreedScreenSetting(breed: String) {
        binding.tvTitle.text = breed
    }

    override fun setLikeBreedScreenSetting() {
        binding.tvTitle.text = getString(R.string.setFirstScreen)
    }

    override fun setFavouritesImageScreenSetting(breed: String) {
        binding.tvTitle.text = breed
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