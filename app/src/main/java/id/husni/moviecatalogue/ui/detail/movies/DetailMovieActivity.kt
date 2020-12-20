package id.husni.moviecatalogue.ui.detail.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.response.MoviesResult
import id.husni.moviecatalogue.ui.detail.DetailCatalogueViewModel
import id.husni.moviecatalogue.ui.favourite.movies.FavMoviesViewModel
import id.husni.moviecatalogue.utils.ApiHelper
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    lateinit var moviesEntity: MoviesEntity
    lateinit var viewModel: DetailCatalogueViewModel
    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = MyCustomViewModel.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailCatalogueViewModel::class.java]

        val extra = intent.extras
        val moviesId = extra?.getString(EXTRA_MOVIE_ID)
        if (moviesId != null) {
            viewModel.setMovieId(moviesId)
            viewModel.getMovies().observe(this, Observer { movies ->
                populateMoviesCatalogue(movies)
            })
        }
    }

    private fun populateMoviesCatalogue(moviesEntity: MoviesResult) {
        tvTitleDetail.text = moviesEntity.title
        tvDateDetail.text = resources.getString(R.string.release_date, moviesEntity.releaseDate)
        tvDetailSummary.text = moviesEntity.overview
        ratingBar.rating = moviesEntity.voteAverage.toFloat().div(2)
        Glide.with(this)
            .load(ApiHelper.IMAGE_POSTER_URL + moviesEntity.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
            .into(imgDetail)
        Glide.with(this)
            .load(ApiHelper.IMAGE_POSTER_URL + moviesEntity.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
            .into(imgBgDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.like_button_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_like ->{
                viewModel.addMoviesFave(moviesEntity)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}