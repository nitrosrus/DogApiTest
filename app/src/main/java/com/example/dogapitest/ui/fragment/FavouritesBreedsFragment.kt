package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.databinding.FavouritesBreedsFragmentBinding
import com.example.dogapitest.mvp.presenter.FavouritesBreedsPresenter
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.mvp.view.FavouritesBreedsView
import com.example.dogapitest.ui.adapter.FavouritesBreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FavouritesBreedsFragment : MvpAppCompatFragment(R.layout.favourites_breeds_fragment),
    FavouritesBreedsView,
    BackButtonListener {
    companion object {
        fun newInstance() = FavouritesBreedsFragment()
    }

    private var _binding: FavouritesBreedsFragmentBinding? = null
    var adapter: FavouritesBreedsRVAdapter? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var presenter: FavouritesBreedsPresenter

    private val breedsComponent = App.instance.breedsComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouritesBreedsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        (activity as DpVisible).setLikeBreedScreenSetting()
    }

    @ProvidePresenter
    fun providePresenter() = FavouritesBreedsPresenter(AndroidSchedulers.mainThread()).apply {
        breedsComponent.inject(this)
    }

    override fun backClicked() = presenter.backClicked()


    override fun init() {
        binding.rvBreeds.layoutManager = LinearLayoutManager(context)
        adapter = FavouritesBreedsRVAdapter(presenter.breedsListPresenter).apply {
            breedsComponent.inject(this)
        }
        binding.rvBreeds.adapter = adapter
    }

    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun setTitle(text: String) {

        // tv_action_title.text = text
    }

    override fun serverErrorInternet() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_server_error, null)
        val btnOk = dialogView.findViewById<Button>(R.id.btn_ok)
        builder.setView(dialogView)
        val dialog = builder.create()
        btnOk.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }


}
