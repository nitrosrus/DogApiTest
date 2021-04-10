package nitrosrus.ru.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.BackButtonListener
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.databinding.FavouritesBreedsFragmentBinding
import nitrosrus.ru.dogapitest.mvp.presenter.FavouritesBreedsPresenter
import nitrosrus.ru.dogapitest.mvp.view.DpVisible
import nitrosrus.ru.dogapitest.mvp.view.FavouritesBreedsView
import nitrosrus.ru.dogapitest.ui.adapter.FavouritesBreedsRVAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FavouritesBreedsFragment : MvpAppCompatFragment(R.layout.favourites_breeds_fragment),
    FavouritesBreedsView, BackButtonListener {

    companion object {
        fun newInstance() = FavouritesBreedsFragment()
    }

    @InjectPresenter
    lateinit var presenter: FavouritesBreedsPresenter

    private var adapter: FavouritesBreedsRVAdapter? = null

    private var _binding: FavouritesBreedsFragmentBinding? = null

    private val binding get() = _binding!!

    private val breedsComponent = App.instance.breedsComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouritesBreedsFragmentBinding.inflate(inflater, container, false)
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
    fun providePresenter() = FavouritesBreedsPresenter().apply {
        breedsComponent.inject(this)
    }

    override fun init() {
        binding.rvBreeds.layoutManager = LinearLayoutManager(context)
        adapter = FavouritesBreedsRVAdapter(presenter.breedsListPresenter).apply {
            breedsComponent.inject(this)
        }
        binding.rvBreeds.adapter = adapter
    }

    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }

    private fun screenSetting() {
        (activity as DpVisible).setLikeBreedScreenSetting()
    }
    override fun backClicked() = presenter.backClicked()
}
