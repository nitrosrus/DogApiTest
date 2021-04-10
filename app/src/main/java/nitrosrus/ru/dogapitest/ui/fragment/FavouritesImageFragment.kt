package nitrosrus.ru.dogapitest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.BackButtonListener
import nitrosrus.ru.dogapitest.R
import nitrosrus.ru.dogapitest.databinding.FavouritesImageFragmentBinding
import nitrosrus.ru.dogapitest.mvp.presenter.FavouritesImagePresenter
import nitrosrus.ru.dogapitest.mvp.view.DpVisible
import nitrosrus.ru.dogapitest.mvp.view.FavouritesImageView
import nitrosrus.ru.dogapitest.ui.adapter.FavouritesImageRVAdapter
import nitrosrus.ru.dogapitest.ui.dialog.IShowAlertDialog
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FavouritesImageFragment : MvpAppCompatFragment(R.layout.favourites_image_fragment),
    FavouritesImageView, BackButtonListener {

    companion object {
        const val IMAGEBREEDS_KEY = "likeimagebreeds"
        fun newInstance(breedName: String) = FavouritesImageFragment().apply {
            arguments = Bundle().apply {
                putString(IMAGEBREEDS_KEY, breedName)
            }
        }
    }


    @InjectPresenter
    lateinit var presenter: FavouritesImagePresenter

    @Inject
    lateinit var dialog: IShowAlertDialog

    private var adapter: FavouritesImageRVAdapter? = null

    private var _binding: FavouritesImageFragmentBinding? = null

    private val binding get() = _binding!!

    private val iComponent = App.instance.imageComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouritesImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iComponent.inject(this)
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
    fun providePresenter() =
        FavouritesImagePresenter(
            arguments!![IMAGEBREEDS_KEY] as String
        ).apply {
            iComponent.inject(this)
        }


    override fun init() {
        binding.rvImage.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = FavouritesImageRVAdapter(presenter.imageListPresenter).apply {
            iComponent.inject(this)
        }
        binding.rvImage.adapter = adapter
    }

    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }


    override fun serverErrorInternet() {

    }

    private fun screenSetting() {
        (activity as DpVisible).setFavouritesImageScreenSetting(
            arguments?.get(IMAGEBREEDS_KEY).toString()
        )
    }

    override fun backClicked() = presenter.backClicked()
}
