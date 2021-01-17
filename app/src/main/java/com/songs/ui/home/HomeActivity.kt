package com.songs.ui.home

import android.os.Bundle
import com.songs.R
import com.songs.base.BaseActivity
import com.songs.data.model.songs_list.SongDetailItem
import com.songs.databinding.ActivityHomeBinding
import com.songs.ui.home.song_detail.SongDetailFragment
import com.songs.ui.home.songs_listing.SongsListingFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(),
    SongsListingFragment.ISongsListingFragmentHost {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openSongsListingFragment()
    }

    private fun openSongsListingFragment() {
        replaceFragment(
            R.id.fl_main, SongsListingFragment.getInstance(),
            SongsListingFragment::class.java.simpleName
        )
    }

    override fun openSongDetailFragment(songDetailItem: SongDetailItem) {
        addFragmentWithBackStackWithAnimation(
            R.id.fl_main, SongDetailFragment.getInstance(songDetailItem),
            SongDetailFragment::class.java.simpleName
        )
    }
}