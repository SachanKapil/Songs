package com.songs.ui.home.song_detail

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

    companion object {
        fun getInstance(songDetailItem: SongDetailItem): SongDetailFragment {
            val gifFullViewFragment = SongDetailFragment()
            val bundle = Bundle().also {
                it.putParcelable(AppConstants.BundleConstants.KEY_SONG_DETAIL, songDetailItem)
            }
            gifFullViewFragment.arguments = bundle
            return gifFullViewFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_song_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        setUpToolbar()
        initListener()
        getBundleData()
    }

    private fun setUpToolbar() {
        binding.appbar.tvTitle.text = getText(R.string.title_song_detail)
    }

    private fun initListener() {
        binding.appbar.ivBack.setOnClickListener(this)
    }

    private fun getBundleData() {
        arguments?.let {
            it.getString(AppConstants.BundleConstants.KEY_SONG_DETAIL)?.let { gifUrl ->
                loadGif(gifUrl)
            }
        }
    }

    private fun loadGif(url: String) {
        ViewUtils.loadGif(
            binding.ivGif,
            binding.pbLoading,
            R.drawable.bg_grey_side_rounded_rectangle,
            url
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.appbar.ivBack.id -> {
                requireActivity().onBackPressed()
            }
        }
    }
}