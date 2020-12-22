package id.husni.moviecatalogue.utils

import androidx.recyclerview.widget.DiffUtil
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

class SeriesDiffUtilCallback(private val oldSeries: List<SeriesEntity>, private val newSeries: List<SeriesEntity>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldSeries.size
    }

    override fun getNewListSize(): Int {
        return newSeries.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSeries[oldItemPosition].id == newSeries[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSeriesFav = oldSeries[oldItemPosition]
        val newSeriesFav = newSeries[newItemPosition]
        return oldSeriesFav.name == newSeriesFav.name
    }
}