package nitrosrus.ru.dogapitest.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.BackButtonListener
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.databinding.BreedsFragmentBinding
import nitrosrus.ru.dogapitest.mvp.presenter.BreedsPresenter
import nitrosrus.ru.dogapitest.mvp.view.BreedsView
import nitrosrus.ru.dogapitest.mvp.view.DpVisible
import nitrosrus.ru.dogapitest.ui.adapter.BreedsRVAdapter
import nitrosrus.ru.dogapitest.ui.dialog.IShowAlertDialog
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class BreedsFragment : MvpAppCompatFragment(R.layout.breeds_fragment),
    BreedsView, BackButtonListener {

    companion object {
        fun newInstance() = BreedsFragment()
    }


    @InjectPresenter
    lateinit var presenter: BreedsPresenter

    @Inject
    lateinit var dialog: IShowAlertDialog

    private var adapter: BreedsRVAdapter? = null

    private var _binding: BreedsFragmentBinding? = null

    private val binding get() = _binding!!

    private val breedsComponent = App.instance.breedsComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BreedsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedsComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        screenSetting()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @ProvidePresenter
    fun providePresenter() = BreedsPresenter().apply {
        breedsComponent.inject(this)
    }

    override fun init() {
        binding.rvBreeds.layoutManager = LinearLayoutManager(requireContext())
        adapter = BreedsRVAdapter(presenter.breedsListPresenter)
            .apply { breedsComponent.inject(this) }
        binding.rvBreeds.adapter = adapter
        dialog.clickListener = { presenter.awaitNetworkStatus() }
        initTextWatcher()
    }

    private fun initTextWatcher() {
        binding.tvFindQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                presenter.breedsListPresenter.textChanged?.invoke(s.toString())

            }
        })
    }

    override fun serverErrorInternet() {
        dialog.getAlertInternet(requireContext()).show()
    }

    private fun screenSetting() {
        (activity as? DpVisible)?.setFirstScreenSetting()
    }

    override fun backClicked() = presenter.backClicked()

    override fun notifyItemRangeInserted(position: Int, count: Int) {
        adapter?.notifyItemRangeInserted(position, count)
    }

    override fun notifyItemRangeRemoved(position: Int, count: Int) {
        adapter?.notifyItemRangeRemoved(position, count)
    }

    override fun notifyItemMoved(fromPosition: Int, toPosition: Int) {
        adapter?.notifyItemMoved(fromPosition, toPosition)
    }

    override fun notifyItemRangeChanged(position: Int, count: Int) {
        adapter?.notifyItemRangeChanged(position, count)
    }

    override fun scrollToPosition(index: Int) {
        binding.rvBreeds.scrollToPosition(index)
    }
}
