package id.husni.moviecatalogue.ui.favourite.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.ui.detail.movies.DetailMovieActivity
import id.husni.moviecatalogue.utils.ApiHelper
import kotlinx.android.synthetic.main.movies_fav_item.view.*

class FavMoviesAdapter : RecyclerView.Adapter<FavMoviesAdapter.ViewHolder>() {

    private val listMovies : MutableList<MoviesEntity> = mutableListOf()

    fun setMoviesFav(items: List<MoviesEntity>) {
        if (items.isNullOrEmpty()) return
        listMovies.clear()
        listMovies.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_fav_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(moviesEntity: MoviesEntity) {
            with(itemView) {
                tvTitleItem.text = moviesEntity.title
                tvDateItem.text = moviesEntity.releaseDate
                Glide.with(context)
                    .load(ApiHelper.IMAGE_POSTER_URL + moviesEntity.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_broken)
                            .error(R.drawable.ic_broken)
                    )
                    .into(imgItem)
                setOnClickListener {
                    val i = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_MOVIE_ID,moviesEntity.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }

    }
}