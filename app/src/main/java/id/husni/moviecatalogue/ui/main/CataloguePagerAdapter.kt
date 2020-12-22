package id.husni.moviecatalogue.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.ui.movies.MoviesFragment
import id.husni.moviecatalogue.ui.series.SeriesFragment

class CataloguePagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object{
        @StringRes
        private val PAGE_TITLE = intArrayOf(R.string.movies,R.string.series)
    }
    override fun getCount(): Int {
        return PAGE_TITLE.size
    }

    override fun getItem(position: Int): Fragment =
            when(position){
                0->MoviesFragment()
                1->SeriesFragment()
                else->Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence {
        return context.getString(PAGE_TITLE[position])
    }
}