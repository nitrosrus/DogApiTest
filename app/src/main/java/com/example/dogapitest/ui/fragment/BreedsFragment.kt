package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.databinding.BreedsFragmentBinding
import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import com.example.dogapitest.ui.dialog.IShowAlertDialog
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class BreedsFragment : MvpAppCompatFragment(R.layout.breeds_fragment),
    BreedsView, BackButtonListener {

    companion object {
        fun newInstance() = BreedsFragment()
    }


    @InjectPresenter
    lateinit var presenter: BreedsPresenter

    @Inject
    lateinit var dialog: IShowAlertDialog

    private var adapter: BreedsRVAdapter? = null

    private var _binding: BreedsFragmentBinding? = null

    private val binding get() = _binding!!

    private val breedsComponent = App.instance.breedsComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BreedsFragmentBinding.inflate(inflater, container, false)
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
    fun providePresenter() = BreedsPresenter().apply {
        breedsComponent.inject(this)
    }

    override fun init() {
        binding.rvBreeds.layoutManager = LinearLayoutManager(requireContext())
        adapter = BreedsRVAdapter(presenter.breedsListPresenter)
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
        (activity as? DpVisible)?.setFirstScreenSetting(R.string.setFirstScreen)
    }

    override fun backClicked() = presenter.backClicked()
}
