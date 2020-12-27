package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.databinding.ItemBreedsBinding
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.presenter.list.ISubBreedListPresenter
import com.example.dogapitest.mvp.view.list.SubBreedsItemView


class SubBreedsRVAdapter(val presenter: ISubBreedListPresenter) :
    RecyclerView.Adapter<SubBreedsRVAdapter.ViewHolder>() {

    lateinit var binding: ItemBreedsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemBreedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        SubBreedsItemView {

        override var pos = -1


        override fun setBreed(text: String) {
            binding.tvBreeds.text = text

        }

        override fun setCountBreed(text: String) {
            binding.tvCountBreeds.text = text

        }

        override fun getBreads(): String {
            return binding.tvBreeds.text.toString()
        }

        override fun setCountVisible() {
            binding.tvCountBreeds.visibility = View.VISIBLE
        }

        override fun setCountInvisible() {
            binding.tvCountBreeds.visibility = View.INVISIBLE
        }

        override fun setClickListener() {
           // binding.tvBreeds.setOnClickListener { presenter.itemClickListener?.invoke(this@ViewHolder) }
            // itemView.tv_breeds.setOnClickListener {  }
        }

    }


}


