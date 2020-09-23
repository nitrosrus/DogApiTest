package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.LikeImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.image_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class LikeImageFragment : MvpAppCompatFragment(), BreedsImageView, BackButtonListener {
    companion object {
        const val IMAGEBREEDS_KEY = "likeimagebreeds"
        fun newInstance(breedName:String) = LikeImageFragment().apply {
            arguments = Bundle().apply {
                putString (IMAGEBREEDS_KEY, breedName)
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

        rv_image.layoutManager = LinearLayoutManager(context)
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


}
