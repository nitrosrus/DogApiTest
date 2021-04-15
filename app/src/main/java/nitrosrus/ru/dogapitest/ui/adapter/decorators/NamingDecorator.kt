package nitrosrus.ru.dogapitest.ui.adapter.decorators

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.ru.dogapitest.mvp.presenter.list.IBreedsListPresenter

class NamingDecorator(val presenter: IBreedsListPresenter) : RecyclerView.ItemDecoration() {
    private val paint = Paint().apply {
        setColor(Color.BLACK)
        textSize = 40f
        typeface = Typeface.DEFAULT_BOLD
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        horizontalDivider(c, parent)
    }
    private fun horizontalDivider(c: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val left =  16
            val top = child.top + 25
            presenter.getChar(parent.getChildViewHolder(child).absoluteAdapterPosition)?.let {
                c.drawText(it, left.toFloat(), top.toFloat(), paint) }
        }
    }

}