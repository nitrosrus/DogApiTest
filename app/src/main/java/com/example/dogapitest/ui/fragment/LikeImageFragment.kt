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
import com.example.dogapitest.databinding.LikeImageFragmentBinding
import com.example.dogapitest.mvp.presenter.LikeImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import com.example.dogapitest.ui.network.ServerErrorInternet
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class LikeImageFragment : MvpAppCompatFragment(R.layout.like_image_fragment), BreedsImageView,
    BackButtonListener {
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

    private var _binding: LikeImageFragmentBinding? = null
    private val binding get() = _binding!!

    private val breedsComponent = App.instance.imageComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LikeImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        (activity as DpVisible).setLikeImageScreenSetting(
            arguments?.get(IMAGEBREEDS_KEY).toString()
        )
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

        binding.rvImage.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = ImageRVAdapter(presenter.imageListPresenter).apply {
            breedsComponent.inject(this)
        }
        binding.rvImage.adapter = adapter

    }


    override fun updateRVAdapter() {
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
