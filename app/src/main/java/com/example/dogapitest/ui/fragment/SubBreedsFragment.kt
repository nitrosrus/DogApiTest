package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.ui.network.ServerErrorInternet
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.SubBreedsView
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.breeds_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SubBreedsFragment : MvpAppCompatFragment(), SubBreedsView, BackButtonListener {

    companion object {
        const val DIALOG_FRAGMENT_TAG = "SubBreedsFragment"
        const val SUBBREEDS_KEY = "subbreeds"
        fun newInstance(subBreeds: String) = SubBreedsFragment().apply {
            arguments = Bundle().apply {
                putString(SUBBREEDS_KEY, subBreeds)
            }
        }

    }


    var adapter: BreedsRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: SubBreedsPresenter


    private val breedsComponent = App.instance.breedsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.sub_breed_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DpVisible).setSubBreedScreenSetting(arguments?.get(SUBBREEDS_KEY).toString())

    }

    @ProvidePresenter
    fun providePresenter() =
        SubBreedsPresenter(
            AndroidSchedulers.mainThread(),
            arguments!![SUBBREEDS_KEY] as String
        ).apply {
            breedsComponent.inject(this)
        }


    override fun backClicked() = presenter.backClicked()

    override fun init() {
        rv_breeds.layoutManager = LinearLayoutManager(context)
        adapter = BreedsRVAdapter(presenter.subBreedsListPresenter).apply {
            breedsComponent.inject(this)
        }
        rv_breeds.adapter = adapter


    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun settitle(text: String) {


    }


    override fun serverErrorInternet() {

        fragmentManager?.let {
            ServerErrorInternet.newInstance().show(
                it, DIALOG_FRAGMENT_TAG
            )
        }

    }

    }


