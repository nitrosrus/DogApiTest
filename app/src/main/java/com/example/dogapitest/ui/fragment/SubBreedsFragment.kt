package com.example.dogapitest.ui.fragment

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.BundleCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import com.jakewharton.rxbinding3.view.visibility
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.breeds_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SubBreedsFragment : MvpAppCompatFragment(), BreedsView, BackButtonListener {

    companion object {
        const val SUBBREEDS_KEY = "subbreeds"
        fun newInstance(subBreeds: ArrayList<String>) = SubBreedsFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(SUBBREEDS_KEY, subBreeds)

            }
        }

    }


    var adapter: BreedsRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: SubBreedsPresenter


    private val breedsComponent = App.instance.breeedsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.sub_breed_fragment, null)






    @ProvidePresenter
    fun providePresenter() =
        SubBreedsPresenter(
            AndroidSchedulers.mainThread(),
            arguments!![SUBBREEDS_KEY] as ArrayList<String>
        ).apply {
            breedsComponent.inject(this)
        }


    override fun backClicked() = presenter.backClicked()

    override fun init() {
        rv_breeds.layoutManager = LinearLayoutManager(context)
        adapter = BreedsRVAdapter(presenter.subBreedsListPresenter).apply {
            breedsComponent.inject(this)
        }
        rv_breeds.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    fun getbreedsname() {

    }

}
