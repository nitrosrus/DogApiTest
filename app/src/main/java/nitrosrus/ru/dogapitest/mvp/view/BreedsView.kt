package nitrosrus.ru.dogapitest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BreedsView : MvpView {
    fun init()
    fun serverErrorInternet()

    fun notifyItemRangeInserted(position: Int, count: Int)
    fun notifyItemRangeRemoved(position: Int, count: Int)
    fun notifyItemMoved(fromPosition: Int, toPosition: Int)
    fun notifyItemRangeChanged(position: Int, count: Int)
    fun scrollToPosition(index: Int)

}