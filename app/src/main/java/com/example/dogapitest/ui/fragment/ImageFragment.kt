package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.mvp.presenter.ImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.image_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ImageFragment : MvpAppCompatFragment(), BreedsImageView, BackButtonListener {
    companion object {
        const val IMAGEBREEDS_KEY = "imagebreeds"
        fun newInstance(list: ArrayList<String>) = ImageFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(IMAGEBREEDS_KEY, list)

//            list.let {
//                listByImage = list
            }
        }
    }

    var adapter: ImageRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: ImagePresenter

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    lateinit var listByImage: List<String>

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
        ImagePresenter(
            AndroidSchedulers.mainThread(),
            arguments!![IMAGEBREEDS_KEY] as ArrayList<String>
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

    override fun setLikeStasus() {

    }

    override fun loadImage(url: String) {

    }


}
