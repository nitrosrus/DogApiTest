package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.databinding.SubBreedFragmentBinding
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.SubBreedsView
import com.example.dogapitest.ui.adapter.SubBreedsRVAdapter
import com.example.dogapitest.ui.dialog.IShowAlertDialog
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class SubBreedsFragment : MvpAppCompatFragment(R.layout.sub_breed_fragment), SubBreedsView,
    BackButtonListener {

    companion object {
        const val SUBBREEDS_KEY = "subbreeds"
        fun newInstance(subBreeds: String) = SubBreedsFragment().apply {
            arguments = Bundle().apply {
                putString(SUBBREEDS_KEY, subBreeds)
            }
        }

    }

    @InjectPresenter
    lateinit var presenter: SubBreedsPresenter

    @Inject
    lateinit var dialog: IShowAlertDialog

    var adapter: SubBreedsRVAdapter? = null

    private var _binding: SubBreedFragmentBinding? = null

    private val binding get() = _binding!!

    private val breedsComponent = App.instance.breedsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = SubBreedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
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
        SubBreedsPresenter(arguments!![SUBBREEDS_KEY] as String)
            .apply { breedsComponent.inject(this) }


    override fun init() {
        binding.rvBreeds.layoutManager = LinearLayoutManager(context)
        adapter = SubBreedsRVAdapter(presenter.subBreedsListPresenter)
            .apply { breedsComponent.inject(this) }
        binding.rvBreeds.adapter = adapter
        dialog.clickListener = { presenter.awaitNetworkStatus() }

    }


    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }


    override fun serverErrorInternet() {
        dialog.getAlertInternet(requireContext()).show()
    }

    private fun screenSetting() {
        (activity as? DpVisible)?.setSubBreedScreenSetting(arguments?.get(SUBBREEDS_KEY).toString())
    }

    override fun backClicked() = presenter.backClicked()
}


