package com.songs.ui.home

import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
import com.songs.R
import com.songs.base.BaseActivity
import com.songs.constants.AppConstants
import com.songs.data.model.songs_list.SongDetailItem
import com.songs.databinding.ActivityHomeBinding
import com.songs.ui.home.song_detail.SongDetailFragment
import com.songs.ui.home.songs_listing.SongsListingFragment
import com.songs.ui.custom_player.PlayerActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(),
    SongsListingFragment.ISongsListingFragmentHost, SongDetailFragment.ISongDetailFragmentHost {

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

    override fun openPlayerActivity(url: String) {
        if (URLUtil.isValidUrl(url)) {
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra(AppConstants.BundleConstants.KEY_DATA, url)
            startActivityWithEnterUpTransition(intent)
        } else {
            showToastShort(getString(R.string.message_invalid_song_url))
        }
    }
}