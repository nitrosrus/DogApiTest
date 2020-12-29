package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.view.list.BreedsItemView


class BreedsRVAdapter(val presenter: IBreedsListPresenter) :
    RecyclerView.Adapter<BreedsRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_breeds, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bind(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        BreedsItemView {

        private val tvBreed: TextView = view.findViewById(R.id.tv_breeds)
        private val tvCountBreed: TextView = view.findViewById(R.id.tv_count_breeds)

        override var pos = -1

        override fun setBreed(breed: String) {
            tvBreed.text = breed
        }

        override fun setCountBreed(countBreed: String) {
            tvCountBreed.text = countBreed
        }

        override fun setClickListener() {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(pos) }
        }


        override fun setCountVisible() {
            tvCountBreed.visibility = View.VISIBLE
        }

        override fun setCountInvisible() {
            tvCountBreed.visibility = View.INVISIBLE
        }

    }


}