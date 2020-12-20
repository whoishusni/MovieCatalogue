package id.husni.moviecatalogue.ui.detail.series

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries
import id.husni.moviecatalogue.ui.detail.DetailCatalogueViewModel
import id.husni.moviecatalogue.utils.ApiHelper
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.activity_detail_series.*
import kotlinx.android.synthetic.main.content_detail_series.*

class DetailSeriesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SERIES_ID = "extra_series_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_series)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = MyCustomViewModel.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailCatalogueViewModel::class.java]

        val extra = intent.extras
        val seriesId = extra?.getString(EXTRA_SERIES_ID)
        if (seriesId != null) {
            viewModel.setSeriesId(seriesId)
            viewModel.getSeries().observe(this, Observer { series ->
                populateSeriesCatalogue(series)
            })
        }
    }

    private fun populateSeriesCatalogue(series: ResultsSeries) {
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}