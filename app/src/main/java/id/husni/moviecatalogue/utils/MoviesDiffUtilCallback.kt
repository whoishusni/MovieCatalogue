package id.husni.moviecatalogue.utils

import androidx.recyclerview.widget.DiffUtil
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity

class MoviesDiffUtilCallback(private val oldFaveMovies: List<MoviesEntity>, private val newFaveMovies: List<MoviesEntity>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldFaveMovies.size
    }

    override fun getNewListSize(): Int {
        return newFaveMovies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFaveMovies[oldItemPosition].id == newFaveMovies[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMoviesFave = oldFaveMovies[oldItemPosition]
        val newMoviesFave = newFaveMovies[newItemPosition]
        return oldMoviesFave.title == newMoviesFave.title
    }
}