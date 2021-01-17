package com.songs.constants

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

object AppConstants {

    object BundleConstants {
        const val KEY_SONG_DETAIL = "key_song_detail"
    }

    object NetworkingConstants {
        const val INTERNAL_SERVER_ERROR_CODE = 500
        const val NO_INTERNET_CONNECTION = 1
    }

    object ApiErrorConstants {
        const val API_ERROR_CODE_INVALID_TOKEN = 419
        const val API_ERROR_CODE_SESSION_EXPIRED = 420
    }

    object PreferenceConstantsKeys {
        const val KEY_GIF_LIST = "key_gif_list"
    }

    object ScreenConstants {
        const val OPEN_HOME_SCREEN = 1
    }

    object PaginationFooterState {
        const val PROGRESS = 1
        const val RETRY = 2
        const val HIDDEN = 3
    }

    object ViewTypeConstants {
        //common adapter view constants
        const val VIEW_TYPE_LOADING = 1
        const val VIEW_TYPE_NORMAL = 2
    }

    object ValueConstants {
        const val PAGINATION_ITEM_LIMIT = 25
    }
}
