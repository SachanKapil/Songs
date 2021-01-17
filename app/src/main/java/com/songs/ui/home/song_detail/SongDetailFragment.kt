package com.songs.ui.home.song_detail

import android.content.Context
import android.os.Bundle
import android.view.View
import com.airhireme.base.BaseFragment
import com.songs.R
import com.songs.constants.AppConstants
import com.songs.data.model.songs_list.SongDetailItem
import com.songs.databinding.FragmentSongDetailBinding
import com.songs.utils.ViewUtils

class SongDetailFragment : BaseFragment<FragmentSongDetailBinding>(), View.OnClickListener {
    private lateinit var binding: FragmentSongDetailBinding
    private lateinit var songDetail: SongDetailItem
    private lateinit var host: ISongDetailFragmentHost

    companion object {
        fun getInstance(songDetailItem: SongDetailItem): SongDetailFragment {
            val songDetailFragment = SongDetailFragment()
            val bundle = Bundle().also {
                it.putParcelable(AppConstants.BundleConstants.KEY_SONG_DETAIL, songDetailItem)
            }
            songDetailFragment.arguments = bundle
            return songDetailFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_song_detail
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ISongDetailFragmentHost) {
            host = context
        } else {
            throw IllegalStateException("host must implement ISongDetailFragmentHost")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleData()
    }

    private fun getBundleData() {
        arguments?.let {
            songDetail = it.getParcelable(AppConstants.BundleConstants.KEY_SONG_DETAIL)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        setUpToolbar()
        initListener()
        setUpUi()
    }

    private fun setUpToolbar() {
        binding.appbar.tvTitle.text = getText(R.string.title_song_detail)
    }

    private fun initListener() {
        binding.appbar.ivBack.setOnClickListener(this)
        binding.ivPlay.setOnClickListener(this)
    }

    private fun setUpUi() {
        ViewUtils.loadImage(
            binding.ivImage,
            null,
            R.drawable.bg_grey_side_rounded_rectangle,
            songDetail.artworkUrl100 ?: "",
            8,
            true
        )

        binding.tvArtistName.text = songDetail.artistName
        binding.tvCollectionName.text = songDetail.collectionName
        binding.tvTrackName.text = songDetail.trackName
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.appbar.ivBack -> {
                requireActivity().onBackPressed()
            }
            binding.ivPlay -> {
                songDetail.previewUrl?.let { host.openPlayerActivity(it) }
            }
        }
    }

    interface ISongDetailFragmentHost {
        fun openPlayerActivity(url: String)
    }
}