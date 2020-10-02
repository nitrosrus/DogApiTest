package com.example.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapitest.R
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.view.list.BreedsItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breeds.view.*

class SubBreedsRVAdapter(val presenter: IBreedsListPresenter) :
    RecyclerView.Adapter<SubBreedsRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_breeds, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, BreedsItemView {
        override var pos = -1

        override fun setBreed(text: String) = with(containerView) {
            tv_breeds.text = text
        }

        override fun setCountBreed(text: String) = with(containerView) {
            tv_count_breeds.text = text
        }


        override fun getBreads(): String = with(containerView) {
            return tv_breeds.text as String
        }

        override fun countVisible(set: Boolean) = with(containerView) {
            if (set) tv_count_breeds.visibility = View.INVISIBLE else tv_count_breeds.visibility =
                View.VISIBLE
        }



    }


}