package nitrosrus.ru.dogapitest.ui.adapter.decorators

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.ru.dogapitest.mvp.presenter.list.IBreedsListPresenter

class NamingDecorator(val presenter: IBreedsListPresenter) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        setColor(Color.BLACK)
        textSize = 40f
        typeface = Typeface.DEFAULT_BOLD
    }

    //вызывается до того как все элементы
    //списка будут отрисованы
    //Метод для отрисовки декора до отрисовки ViewHolder
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        horizontalDivider(c, parent)
    }


    //после всех элементов...
    //Метод для отрисовки декора после отрисовки ViewHolder
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }


    //определяет расстояние, которое
    //будет между элементами списка
    //Метод для выставления отступов у ViewHolder при заполнении RecyclerView
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

    }

    private fun horizontalDivider(c: Canvas, parent: RecyclerView) {

        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {

            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            // val top = child.bottom + params.bottomMargin
            val left = parent.paddingLeft + 18
            val top = child.top + 30
            presenter.getChar(parent.getChildViewHolder(child).adapterPosition)?.let { c.drawText(it, left.toFloat(), top.toFloat(), paint) }

        }
    }


}