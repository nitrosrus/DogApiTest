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
import com.example.dogapitest.ui.network.ServerErrorInternet
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.SubBreedsView
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import com.example.dogapitest.ui.adapter.SubBreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SubBreedsFragment : MvpAppCompatFragment(R.layout.sub_breed_fragment), SubBreedsView,
    BackButtonListener {

    companion object {
        const val DIALOG_FRAGMENT_TAG = "SubBreedsFragment"
        const val SUBBREEDS_KEY = "subbreeds"
        fun newInstance(subBreeds: String) = SubBreedsFragment().apply {
            arguments = Bundle().apply {
                putString(SUBBREEDS_KEY, subBreeds)
            }
        }

    }


    var adapter: SubBreedsRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: SubBreedsPresenter

    private var _binding: SubBreedFragmentBinding? = null

    private val binding get() = _binding!!

    private val breedsComponent = App.instance.breedsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = SubBreedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
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
        binding.rvBreeds.layoutManager = LinearLayoutManager(context)
        adapter = SubBreedsRVAdapter(presenter.subBreedsListPresenter).apply {
            breedsComponent.inject(this)
        }
        binding.rvBreeds.adapter = adapter


    }


    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }

    override fun setTitle(text: String) {


    }


    override fun serverErrorInternet() {

        fragmentManager?.let {
            ServerErrorInternet.newInstance().show(
                it, DIALOG_FRAGMENT_TAG
            )
        }

    }

}


