package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.ImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import com.example.dogapitest.ui.network.ServerErrorInternet
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.image_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ImageFragment : MvpAppCompatFragment(), BreedsImageView, BackButtonListener {
    companion object {
        const val IMAGEBREEDS_KEY = "imagebreeds"
        const val IMAGESUBBREEDS_KEY = "imagesubbreds"
        fun newInstance(breed: String, subBreeds: String?) = ImageFragment().apply {
            arguments = Bundle().apply {
                putString(IMAGEBREEDS_KEY, breed)
                putString(IMAGESUBBREEDS_KEY, subBreeds)

            }
        }


    }

    var adapter: ImageRVAdapter? = null


    @InjectPresenter
    lateinit var presenter: ImagePresenter


    private val breedsComponent = App.instance.imageComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.image_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        setTing()

    }

    fun setTing() {
        if (presenter.oneOrTwo())
        (activity as DpVisible).setImageBreedScreenSetting(
            arguments?.get(IMAGEBREEDS_KEY).toString()
        )
        else
            (activity as DpVisible).setImageBreedScreenSetting(
                arguments?.get(IMAGEBREEDS_KEY).toString(),
                arguments?.get(IMAGESUBBREEDS_KEY).toString()
            )


    }

    @ProvidePresenter
    fun providePresenter() =
        ImagePresenter(
            AndroidSchedulers.mainThread(),
            arguments!![IMAGEBREEDS_KEY] as String,
            arguments!![IMAGESUBBREEDS_KEY] as String?
        ).apply {
            breedsComponent.inject(this)
        }

    override fun backClicked() = presenter.backClicked()

    override fun init() {

        rv_image.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = ImageRVAdapter(presenter.imageListPresenter).apply {
            breedsComponent.inject(this)
        }
        rv_image.adapter = adapter

    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun loadImage(url: String) {

    }

    override fun serverErrorInternet() {
        fragmentManager?.let {
            ServerErrorInternet.newInstance().show(
                it, SubBreedsFragment.DIALOG_FRAGMENT_TAG
            )
        }
    }




}
