package id.husni.moviecatalogue.ui.favourite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.ui.favourite.movies.FavMoviesFragment
import id.husni.moviecatalogue.ui.favourite.series.FavSeriesFragment

class FavCataloguePagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    companion object {
        @StringRes
        private val PAGE_TITLE = intArrayOf(R.string.movies, R.string.series)
    }

    override fun getCount(): Int {
        return PAGE_TITLE.size
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMoviesFragment()
            1 -> FavSeriesFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(PAGE_TITLE[position])
    }
}