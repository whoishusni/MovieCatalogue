package id.husni.moviecatalogue.ui.favourite.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.ui.detail.movies.DetailMovieActivity
import id.husni.moviecatalogue.utils.ApiHelper
import kotlinx.android.synthetic.main.movies_fav_item.view.*

class FavMoviesAdapter : PagedListAdapter<MoviesEntity, FavMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MoviesEntity> = object : DiffUtil.ItemCallback<MoviesEntity>(){
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMoviesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_fav_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMoviesAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position) as MoviesEntity)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(moviesEntity: MoviesEntity) {
            with(itemView){
                tvTitleItem.text = moviesEntity.title
                tvDateItem.text = moviesEntity.releaseDate
                Glide.with(context)
                    .load(ApiHelper.IMAGE_POSTER_URL + moviesEntity.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
                    .into(imgItem)
                setOnClickListener {
                    val i = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, moviesEntity.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }
    }
}