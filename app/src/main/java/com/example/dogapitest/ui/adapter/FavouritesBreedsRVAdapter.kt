package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.databinding.ItemBreedsBinding
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.presenter.list.IFavouritesBreedsListPresenter
import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.mvp.view.list.FavouritesBreedsItemView


class FavouritesBreedsRVAdapter(val presenter: IFavouritesBreedsListPresenter) :
    RecyclerView.Adapter<FavouritesBreedsRVAdapter.ViewHolder>() {

    lateinit var binding: ItemBreedsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemBreedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
      //  binding.tvBreeds.setOnClickListener { presenter.itemClickListener?.invoke( ) }
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        FavouritesBreedsItemView {

        override var pos = -1


        override fun setBreed(text: String)  {
            binding.tvBreeds.text=text

        }

        override fun setCountBreed(text: String)  {
            binding.tvCountBreeds.text=text

        }

        override fun getBreads(): String  {
            return binding.tvBreeds.text.toString()
        }

        override fun setCountVisible() {
             binding.tvCountBreeds.visibility = View.VISIBLE
        }

        override fun setCountInvisible() {
            binding.tvCountBreeds.visibility = View.INVISIBLE
        }

        override fun setClickListener() {
            binding.tvBreeds.setOnClickListener { presenter.itemClick?.invoke(this@FavouritesBreedsRVAdapter.binding.tvBreeds.toString()) }
           // itemView.tv_breeds.setOnClickListener {  }
        }

    }


}