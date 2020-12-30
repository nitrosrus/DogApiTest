package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.R
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.mvp.presenter.list.IImageListPresenter
import com.example.dogapitest.mvp.view.list.ImageItemView
import javax.inject.Inject

class ImageRVAdapter(val presenter: IImageListPresenter) :
    RecyclerView.Adapter<ImageRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bind(holder)

    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), ImageItemView {

        private val btnLike: ImageButton = view.findViewById(R.id.btn_like)
        private val ivImageBreed: ImageView = view.findViewById(R.id.iv_imageBreed)
        private val ivImageLoader: ImageView = view.findViewById(R.id.iv_loadImage)


        override var pos = -1

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, ivImageBreed, ivImageLoader)
        }

        override fun setLikeEnable() {
            btnLike.setImageResource(R.drawable.ic_heart_font)
        }

        override fun setLikeDisable() {
            btnLike.setImageResource(R.drawable.ic_heart)
        }

        override fun setClickListener() {
            btnLike.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }
}