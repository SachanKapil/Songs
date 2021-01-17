package com.songs.ui.home.songs_listing

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airhireme.base.BaseFragment
import com.songs.R
import com.songs.constants.AppConstants
import com.songs.custom_works.GridSpaceItemDecoration
import com.songs.data.model.songs_list.SongDetailItem
import com.songs.databinding.FragmentSongsListingBinding
import com.songs.utils.AppUtils
import com.songs.utils.inVisible
import kotlinx.android.synthetic.main.layout_progress_bar.view.*

class SongsListingFragment : BaseFragment<FragmentSongsListingBinding>(),
    SwipeRefreshLayout.OnRefreshListener, SongsListingAdapter.SongsListingAdapterListener,
    View.OnClickListener {
    private lateinit var binding: FragmentSongsListingBinding
    private lateinit var host: ISongsListingFragmentHost
    private lateinit var adapter: SongsListingAdapter
    private lateinit var viewModel: SongsListingViewModel

    companion object {
        fun getInstance(): SongsListingFragment {
            return SongsListingFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_songs_listing
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ISongsListingFragmentHost) {
            host = context
        } else {
            throw IllegalStateException("host must implement ISongsListingFragmentHost")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initViewModel()
    }

    private fun initAdapter() {
        adapter = SongsListingAdapter(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[SongsListingViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        setUpToolbar()
        initListener()
        initRecyclerView()
        initObservers()

        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.progressBarLayout.showLoading()
            hitGetSongsListApi()
        } else {
            binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_no_internet_connection))
        }
    }

    private fun setUpToolbar() {
        binding.appbar.ivBack.inVisible()
        binding.appbar.tvTitle.text = getText(R.string.title_songs_list)
    }

    private fun initListener() {
        binding.refreshLayout.setOnRefreshListener(this)
        binding.refreshLayout.setColorSchemeResources(R.color.orange)
        binding.progressBarLayout.btnTryAgain.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        binding.rvGif.adapter = adapter

        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    AppConstants.ViewTypeConstants.VIEW_TYPE_NORMAL -> 1
                    AppConstants.ViewTypeConstants.VIEW_TYPE_LOADING -> 2
                    else -> 1
                }
            }
        }
        val space8Dp = AppUtils.convertDpToPixel(8f, requireContext()).toInt()
        binding.rvGif.addItemDecoration(
            GridSpaceItemDecoration(space8Dp * 2, space8Dp, space8Dp, space8Dp)
        )

        binding.rvGif.layoutManager = layoutManager
    }


    private fun initObservers() {
        viewModel.getSongsListResponseLiveData()
            .observe(viewLifecycleOwner, { wrappedResponseEvent ->
                if (wrappedResponseEvent != null && !wrappedResponseEvent.isAlreadyHandled) {
                    binding.progressBarLayout.hideLoading()
                    binding.refreshLayout.isRefreshing = false
                    val objectWrappedResponse = wrappedResponseEvent.getContent()
                    objectWrappedResponse?.failureResponse?.let {
                        onFailure(it)
                    } ?: let {
                        objectWrappedResponse?.data?.let { songsList ->
                            if (songsList.size != 0) {
                                adapter.loadData(songsList)
                            } else {
                                adapter.clearAllData()
                                binding.progressBarLayout.setErrorWithOutRetryButton(
                                    getString(R.string.message_no_data_found)
                                )
                            }
                        }
                    }
                }
            })
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.progressBarLayout.btnTryAgain -> {
                if (AppUtils.isNetworkAvailable(requireContext())) {
                    binding.progressBarLayout.showLoading()
                    hitGetSongsListApi()
                } else {
                    showToastShort(getString(R.string.message_no_internet_connection))
                    binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_no_internet_connection))
                }
            }
        }
    }

    override fun onRefresh() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            hitGetSongsListApi()
        } else {
            binding.refreshLayout.isRefreshing = false
            showToastShort(getString(R.string.message_no_internet_connection))
        }
    }

    private fun hitGetSongsListApi() {
        viewModel.hitGetSongsListApi()
    }

    override fun onSongItemClick(songDetailItem: SongDetailItem) {
        host.openSongDetailFragment(songDetailItem)
    }

    interface ISongsListingFragmentHost {
        fun openSongDetailFragment(songDetailItem: SongDetailItem)
    }
}