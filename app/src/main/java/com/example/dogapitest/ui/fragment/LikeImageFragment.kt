package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.LikeImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import com.example.dogapitest.ui.network.ServerErrorInternet
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.image_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class LikeImageFragment : MvpAppCompatFragment(), BreedsImageView, BackButtonListener {
    companion object {
        const val IMAGEBREEDS_KEY = "likeimagebreeds"
        fun newInstance(breedName: String) = LikeImageFragment().apply {
            arguments = Bundle().apply {
                putString(IMAGEBREEDS_KEY, breedName)
            }
        }


    }

    var adapter: ImageRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: LikeImagePresenter


    private val breedsComponent = App.instance.imageComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.image_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        (activity as DpVisible).setLikeImageScreenSetting(arguments?.get(IMAGEBREEDS_KEY).toString())
    }

    @ProvidePresenter
    fun providePresenter() =
        LikeImagePresenter(
            AndroidSchedulers.mainThread(),
            arguments!![IMAGEBREEDS_KEY] as String
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
