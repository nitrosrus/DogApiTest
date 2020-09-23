package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.R
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.mvp.presenter.list.IImageListPresenter
import com.example.dogapitest.mvp.view.list.ImageItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image.view.*
import javax.inject.Inject

class ImageRVAdapter(val presenter: IImageListPresenter) :
    RecyclerView.Adapter<ImageRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        holder.containerView.btn_like.setOnClickListener { presenter.likeBTN(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, ImageItemView {
        override var pos = -1
        override fun setlike(boolean: Boolean) = with(containerView) {
            if (boolean){
                btn_like.setImageResource(R.drawable.ic_heart_font)
            }else{
                btn_like.setImageResource(R.drawable.ic_heart)
            }
        }

        override fun loadImage(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_imageBreed)
        }


    }


}