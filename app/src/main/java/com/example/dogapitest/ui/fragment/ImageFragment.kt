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
import com.example.dogapitest.databinding.ImageFragmentBinding
import com.example.dogapitest.mvp.presenter.ImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ImageFragment : MvpAppCompatFragment(R.layout.image_fragment), BreedsImageView,
    BackButtonListener {
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

    private var _binding: ImageFragmentBinding? = null

    private val binding get() = _binding!!

    private val breedsComponent = App.instance.imageComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ImageFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        setTing()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
//        fragmentManager?.let {
//            ShowAlertDialog.newInstance().show(
//                it, SubBreedsFragment.DIALOG_FRAGMENT_TAG
//            )
//        }
    }


}
