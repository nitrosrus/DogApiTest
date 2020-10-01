package com.example.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.App
import com.example.dogapitest.BackButtonListener
import com.example.dogapitest.R
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.mvp.presenter.ImagePresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.DpVisible
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.image_fragment.*
import kotlinx.android.synthetic.main.item_image.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ImageFragment : MvpAppCompatFragment(), BreedsImageView, BackButtonListener {
    companion object {
        const val IMAGEBREEDS_KEY = "imagebreeds"
        fun newInstance(list: ArrayList<String>) = ImageFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(IMAGEBREEDS_KEY, list)
            }
        }
    }

    var adapter: ImageRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: ImagePresenter


    private val breedsComponent = App.instance.imageComponent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.image_fragment, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
        (activity as? DpVisible)?.setImageBreedScreenSetting(
            arguments?.get(IMAGEBREEDS_KEY).toString()
        )

    }

    @ProvidePresenter
    fun providePresenter() =
        ImagePresenter(
            AndroidSchedulers.mainThread(),
            arguments!![IMAGEBREEDS_KEY] as ArrayList<String>
        ).apply {
            breedsComponent.inject(this)
        }

    override fun backClicked() = presenter.backClicked()

    override fun init() {
        rv_image.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        adapter = ImageRVAdapter(presenter.imageListPresenter).apply {
            breedsComponent.inject(this)
        }
        rv_image.adapter = adapter

    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun loadImage(url: String) {

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

    override fun shareImage() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_share_photo, null)
        val btnShare = dialogView.findViewById<Button>(R.id.btn_share)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)
        builder.setView(dialogView)
        val dialog = builder.create()
        btnShare.setOnClickListener {dialog.dismiss() }
        btnCancel.setOnClickListener {dialog.dismiss() }
        dialog.show()
    }


}
