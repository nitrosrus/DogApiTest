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
import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.breeds_fragment.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class BreedsFragment : MvpAppCompatFragment(), BreedsView, BackButtonListener {
    companion object {
        fun newInstance() = BreedsFragment()
    }

    var adapter: BreedsRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: BreedsPresenter

    private val breedsComponent = App.instance.breedsComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.breeds_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        (activity as? DpVisible)?.setFerstScreenSetting("Breeds")

    }

    @ProvidePresenter
    fun providePresenter() = BreedsPresenter(AndroidSchedulers.mainThread()).apply {
        breedsComponent.inject(this)
    }

    override fun backClicked() = presenter.backClicked()


    override fun init() {
        rv_breeds.layoutManager = LinearLayoutManager(context)
        adapter = BreedsRVAdapter(presenter.breedsListPresenter).apply {
            breedsComponent.inject(this)
        }

        rv_breeds.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun settitle(text: String) {
        tv_action_title.text = text
    }

    override fun serverErrorInternet() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_server_error, null)
        val btnOk = dialogView.findViewById<Button>(R.id.btn_ok)
        builder.setView(dialogView)
        val dialog = builder.create()
        btnOk.setOnClickListener {dialog.dismiss() }
        dialog.show()
    }


}
