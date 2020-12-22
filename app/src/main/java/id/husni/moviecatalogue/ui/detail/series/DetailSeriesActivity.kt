package id.husni.moviecatalogue.ui.detail.series

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import id.husni.moviecatalogue.ui.detail.DetailCatalogueViewModel
import id.husni.moviecatalogue.utils.ApiHelper
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.activity_detail_series.*
import kotlinx.android.synthetic.main.content_detail_series.*

class DetailSeriesActivity : AppCompatActivity() {
    private lateinit var seriesEntity: SeriesEntity
    private lateinit var viewModel: DetailCatalogueViewModel
    private lateinit var menu: Menu
    companion object {
        const val EXTRA_SERIES_ID = "extra_series_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_series)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel(this)
        val extra = intent.extras
        val seriesId = extra?.getString(EXTRA_SERIES_ID)
        if (seriesId != null) {
            viewModel.setSeriesId(seriesId)
            viewModel.getSeries().observe(this, Observer { series ->
                seriesEntity = series
                populateSeriesCatalogue(series)
            })
        }
    }
    private fun populateSeriesCatalogue(series: SeriesEntity) {
        tvTitleDetail.text = series.name
        tvDateDetail.text = resources.getString(R.string.airing_date, series.firstAirDate)
        tvDetailSummary.text = series.overview
        ratingBar.rating = series.voteAverage.toFloat() / 2
        Glide.with(this)
            .load(ApiHelper.IMAGE_POSTER_URL + series.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
            .into(imgBgDetail)
        Glide.with(this)
            .load(ApiHelper.IMAGE_POSTER_URL + series.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
            .into(imgDetail)

        favouriteState()
    }

    private fun obtainViewModel(activity: DetailSeriesActivity): DetailCatalogueViewModel {
        val factory = MyCustomViewModel.getInstance(activity.application)
        return ViewModelProvider(this,factory)[DetailCatalogueViewModel::class.java]
    }

    private fun favouriteState() {
        val menuItem = menu.findItem(R.id.action_like)
        if (viewModel.isSeriesBookmarked(seriesEntity)){
            menuItem.icon = ContextCompat.getDrawable(this,R.drawable.ic_bookmarked)
        } else{
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.like_button_menu, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.action_like -> {
                if (viewModel.isSeriesBookmarked(seriesEntity)) {
                    viewModel.deleteSeriesFav(seriesEntity)
                    Toast.makeText(this, getString(R.string.removed_fave, seriesEntity.name), Toast.LENGTH_SHORT).show()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark)
                } else {
                    viewModel.addSeriesFav(seriesEntity)
                    Toast.makeText(this,getString(R.string.added_fave, seriesEntity.name), Toast.LENGTH_SHORT).show()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked)
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
}