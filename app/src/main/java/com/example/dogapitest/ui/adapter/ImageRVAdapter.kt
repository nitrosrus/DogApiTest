package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.R
import com.example.dogapitest.databinding.ItemImageBinding
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.mvp.presenter.list.IImageListPresenter
import com.example.dogapitest.mvp.view.list.ImageItemView

import javax.inject.Inject

class ImageRVAdapter(val presenter: IImageListPresenter) :
    RecyclerView.Adapter<ImageRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    lateinit var binding: ItemImageBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        binding.btnLike.setOnClickListener { presenter.likeBTN(holder) }

        //holder.containerView.btn_like.setOnClickListener { presenter.likeBTN(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        ImageItemView {
        override var pos = -1
        override fun setlike(boolean: Boolean) = with(containerView) {
            if (boolean) {
                binding.btnLike.setImageResource(R.drawable.ic_heart_font)
            } else {
                binding.btnLike.setImageResource(R.drawable.ic_heart)
            }
        }

        override fun loadImage(url: String) = with(containerView) {
            imageLoader.loadInto(url, binding.ivImageBreed, binding.ivLoadImage)
        }


    }

    interface OnListItemClickListener {
        fun onItemClick()
    }

}