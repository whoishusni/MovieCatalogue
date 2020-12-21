package id.husni.moviecatalogue.ui.movies

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
import kotlinx.android.synthetic.main.movies_item.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies = ArrayList<MoviesEntity>()

    fun setMovies(items : List<MoviesEntity>?){
        if (items.isNullOrEmpty()) return
        movies.clear()
        movies.addAll(items)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(resultMovies: MoviesEntity) {
            with(itemView){
                tvTitleItem.text = resultMovies.title
                tvDateItem.text = resultMovies.releaseDate
                Glide.with(context)
                        .load(ApiHelper.IMAGE_POSTER_URL + resultMovies.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
                        .into(imgItem)
                setOnClickListener {
                    val i = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_MOVIE_ID,resultMovies.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }

    }
}