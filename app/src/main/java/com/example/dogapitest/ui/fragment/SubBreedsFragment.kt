package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.breeds_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SubBreedsFragment : MvpAppCompatFragment(), BreedsView, BackButtonListener {
    companion object {
        fun newInstance(breeds: List<String>) = SubBreedsFragment().apply {
            breeds.let {
                breed = breeds
            }
        }

    }

    var adapter: BreedsRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: SubBreedsPresenter

    lateinit var breed: List<String>

    private val breedsComponent = App.instance.breeedsComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.sub_breed_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
    }

    @ProvidePresenter
    fun providePresenter() = SubBreedsPresenter(AndroidSchedulers.mainThread(), breed).apply {
        breedsComponent.inject(this)
    }

    override fun backClicked() = presenter.backClicked()

    override fun init() {
        rv_breeds.layoutManager = LinearLayoutManager(context)
        adapter = BreedsRVAdapter(presenter).apply {
            breedsComponent.inject(this)
        }
        rv_breeds.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


}
