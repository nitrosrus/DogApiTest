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
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.ImageView
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import com.example.dogapitest.ui.dialog.IShowAlertDialog
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ImageFragment : MvpAppCompatFragment(R.layout.image_fragment), ImageView,
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

    @InjectPresenter
    lateinit var presenter: ImagePresenter

    @Inject
    lateinit var dialog: IShowAlertDialog

    var adapter: ImageRVAdapter? = null

    private var _binding: ImageFragmentBinding? = null

    private val binding get() = _binding!!

    private val iComponent = App.instance.imageComponent

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
        iComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        screenSetting()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @ProvidePresenter
    fun providePresenter() =
        ImagePresenter(
            arguments!![IMAGEBREEDS_KEY] as String,
            arguments!![IMAGESUBBREEDS_KEY] as String?
        ).apply {
            iComponent.inject(this)
        }


    override fun init() {

        binding.rvImage.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = ImageRVAdapter(presenter.imageListPresenter).apply {
            iComponent.inject(this)
        }
        binding.rvImage.adapter = adapter
        dialog.clickListener = { presenter.awaitNetworkStatus() }
    }


    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }


    override fun serverErrorInternet() {
        dialog.getAlertInternet(requireContext()).show()

    }

    private fun screenSetting() {

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

    override fun backClicked() = presenter.backClicked()
}

