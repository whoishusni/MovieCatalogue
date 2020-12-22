package id.husni.moviecatalogue.ui.detail.movies

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
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.ui.detail.DetailCatalogueViewModel
import id.husni.moviecatalogue.utils.ApiHelper
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var moviesEntity: MoviesEntity
    private lateinit var viewModel: DetailCatalogueViewModel
    private lateinit var menu: Menu
    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)

        viewModel = obtainViewModel(this)
        val extra = intent.extras
        val moviesId = extra?.getString(EXTRA_MOVIE_ID)
        if (moviesId != null) {
            viewModel.setMovieId(moviesId)
            viewModel.getMovies().observe(this, Observer { movies ->
                moviesEntity = movies
                populateMoviesCatalogue(movies)
            })
        }
    }

    private fun obtainViewModel(activity: DetailMovieActivity): DetailCatalogueViewModel{
        val factory = MyCustomViewModel.getInstance(activity.application)
        return ViewModelProvider(this, factory)[DetailCatalogueViewModel::class.java]
    }

    private fun populateMoviesCatalogue(entityMovies: MoviesEntity) {
        tvTitleDetail.text = entityMovies.title
        tvDateDetail.text = resources.getString(R.string.release_date, entityMovies.releaseDate)
        tvDetailSummary.text = entityMovies.overview
        ratingBar.rating = entityMovies.voteAverage.toFloat().div(2)
        Glide.with(this)
            .load(ApiHelper.IMAGE_POSTER_URL + entityMovies.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
            .into(imgDetail)
        Glide.with(this)
            .load(ApiHelper.IMAGE_POSTER_URL + entityMovies.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
            .into(imgBgDetail)

        favouriteState()
    }

    private fun favouriteState() {
        val menuItem = menu.findItem(R.id.action_like)
        if (viewModel.isMovieBookmarked(moviesEntity)){
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked)
        }else{
            menuItem.icon = ContextCompat.getDrawable(this,R.drawable.ic_bookmark)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.like_button_menu,menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
            R.id.action_like ->{
                if(viewModel.isMovieBookmarked(moviesEntity)){
                    viewModel.deleteMoviesFave(moviesEntity)
                    Toast.makeText(this, getString(R.string.removed_fave,moviesEntity.title), Toast.LENGTH_SHORT).show()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark)
                } else{
                    viewModel.addMoviesFave(moviesEntity)
                    Toast.makeText(this,getString(R.string.added_fave, moviesEntity.title),Toast.LENGTH_SHORT).show()
                    item.icon = ContextCompat.getDrawable(this,R.drawable.ic_bookmarked)
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
}