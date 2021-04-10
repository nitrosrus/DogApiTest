package nitrosrus.ru.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.mvp.model.image.IImageLoader
import nitrosrus.ru.dogapitest.mvp.presenter.list.IFavouritesImageListPresenter
import nitrosrus.ru.dogapitest.mvp.view.list.FavouritesImageItemView
import javax.inject.Inject

class FavouritesImageRVAdapter(val presenter: IFavouritesImageListPresenter) :
    RecyclerView.Adapter<FavouritesImageRVAdapter.ViewHolder>() {

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


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view),
        FavouritesImageItemView {

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

        override fun setClickListener() {
            btnLike.setOnClickListener { presenter.itemClickListener?.invoke(pos) }
        }

    }

}