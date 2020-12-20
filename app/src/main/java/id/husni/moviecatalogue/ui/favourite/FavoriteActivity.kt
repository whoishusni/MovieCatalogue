package id.husni.moviecatalogue.ui.favourite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import id.husni.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setSupportActionBar(favouriteToolbar)
        supportActionBar?.title = getString(R.string.favourite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPagerAdapter = FavCataloguePagerAdapter(this, supportFragmentManager)
        favouriteViewPager.adapter = viewPagerAdapter
        favTabLayout.setupWithViewPager(favouriteViewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}