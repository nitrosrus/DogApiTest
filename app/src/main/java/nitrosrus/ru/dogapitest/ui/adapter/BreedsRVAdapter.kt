package nitrosrus.ru.dogapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.mvp.presenter.list.IBreedsListPresenter
import nitrosrus.ru.dogapitest.mvp.view.list.BreedsItemView


class BreedsRVAdapter(val presenter: IBreedsListPresenter) :
    RecyclerView.Adapter<BreedsRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_breeds, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bind(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), BreedsItemView {

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


//class ForwardContactAdapter(val presenter: ForwardContactContract.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ForwardContactHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact_forwarding, parent, false))
//
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        presenter.bind(holder as ForwardContactContract.Item, position)
//    }
//
//    override fun getItemCount() = presenter.getCount()
//
//
//    inner class ForwardContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ForwardContactContract.Item {
//        val name: TextView = itemView.findViewById(R.id.tvName)
//        val username: TextView = itemView.findViewById(R.id.tvUsername)
//        val avatarImage: ImageView = itemView.findViewById(R.id.avatar)
//        val cbSelectContact: CheckBox = itemView.findViewById(R.id.cbSelectContact)
//        override fun getName(): String {
//            return name.text.toString()
//        }
//
//        override fun setName(nameIn: String?) {
//            nameIn?.let { name.text = it }
//        }
//
//        override fun setUsername(usernameIn: String?) {
//            usernameIn?.let { username.text = it }
//        }
//
//        override fun setAvatar(url: String?) {
//            if (url?.trim().isNullOrEmpty()) {
//                avatarImage.setImageResource(R.drawable.ic_profile)
//            } else {
//                initAvatar(url!!)
//            }
//        }
//
//        override fun setCheckBox(status: Boolean) {
//            cbSelectContact.isChecked = status
//        }
//
//        override fun setClickListener() {
//            cbSelectContact.setOnClickListener {
//                presenter.checkBoxClicked(position)
//            }
//        }
//
//        private fun initAvatar(url: String) {
//            if (!url.trim().isNullOrEmpty()) {
//                var glideUrl = GlideUrl(
//                    url,
//                    LazyHeaders.Builder()
//                        .addHeader(ApiEndPoints.ACCESS_TOKEN, PrefUtils.getToken())
//                        .build()
//                )
//                setImage(glideUrl)
//
//            }
//
//        }
//
//        private fun setImage(avatar: Any?) {
//            Glide.with(itemView.context)
//                .load(avatar)
//                .into(avatarImage)
//        }
//
//    }
//
//}
//
//class PhoneBookItemDecoration(val presenter: ForwardContactContract.Presenter) : RecyclerView.ItemDecoration() {
//    private val paint = Paint().apply {
//        setColor(Color.BLACK)
//        textSize = 40f
//
//        typeface = Typeface.DEFAULT_BOLD
//    }
//
//    //вызывается до того как все элементы
//    //списка будут отрисованы
//    //Метод для отрисовки декора до отрисовки ViewHolder
//    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(c, parent, state)
//        horizontalDivider(c, parent)
//    }
//
//
//    //после всех элементов...
//    //Метод для отрисовки декора после отрисовки ViewHolder
//    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDrawOver(c, parent, state)
//    }
//
//
//    //определяет расстояние, которое
//    //будет между элементами списка
//    //Метод для выставления отступов у ViewHolder при заполнении RecyclerView
//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        super.getItemOffsets(outRect, view, parent, state)
//
//    }
//
//    private fun horizontalDivider(c: Canvas, parent: RecyclerView) {
//
//        val right = parent.width - parent.paddingRight
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//
//            val child = parent.getChildAt(i)
//            val params = child.layoutParams as RecyclerView.LayoutParams
//            // val top = child.bottom + params.bottomMargin
//            val left = parent.paddingLeft + 18
//            val top = child.top + 30
//            presenter.getChar(parent.getChildViewHolder(child).adapterPosition)?.let { c.drawText(it, left.toFloat(), top.toFloat(), paint) }
//
//        }
//    }
//
//
//}


