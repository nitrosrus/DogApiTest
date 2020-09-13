package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.breeds_fragment.*
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

    private val breedsComponent = App.instance.breeedsComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.breeds_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
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




}
