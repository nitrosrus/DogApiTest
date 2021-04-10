package nitrosrus.ru.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.mvp.presenter.list.ISubBreedListPresenter
import nitrosrus.ru.dogapitest.mvp.view.list.SubBreedsItemView


class SubBreedsRVAdapter(val presenter: ISubBreedListPresenter) :
    RecyclerView.Adapter<SubBreedsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

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
        SubBreedsItemView {

        private val tvBreed: TextView = view.findViewById(R.id.tv_breeds)
        private val tvCountBreed: TextView = view.findViewById(R.id.tv_count_breeds)

        override var pos = -1

        override fun setBreed(breed: String) {
            tvBreed.text = breed
        }

        override fun setClickListener() {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(pos) }
        }

    }

}



