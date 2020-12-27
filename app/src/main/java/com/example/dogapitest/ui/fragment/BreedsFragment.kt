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
import com.example.dogapitest.databinding.BreedsFragmentBinding
import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class BreedsFragment : MvpAppCompatFragment(R.layout.breeds_fragment),
    BreedsView, BackButtonListener {

    companion object {
        fun newInstance() = BreedsFragment()
    }


    @InjectPresenter
    lateinit var presenter: BreedsPresenter

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
    fun providePresenter() = BreedsPresenter(AndroidSchedulers.mainThread()).apply {
        breedsComponent.inject(this)
    }

    override fun init() {
        binding.rvBreeds.layoutManager = LinearLayoutManager(context)
        adapter = BreedsRVAdapter(presenter.breedsListPresenter, requireContext())
            .apply { breedsComponent.inject(this) }
        binding.rvBreeds.adapter = adapter
    }


    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
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

    private fun screenSetting() {
        (activity as? DpVisible)?.setFirstScreenSetting(R.string.setFirstScreen)
    }

    override fun backClicked() = presenter.backClicked()
}
