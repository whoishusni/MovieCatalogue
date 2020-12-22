package id.husni.moviecatalogue.ui.favourite.series

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import id.husni.moviecatalogue.ui.detail.series.DetailSeriesActivity
import id.husni.moviecatalogue.utils.ApiHelper
import id.husni.moviecatalogue.utils.SeriesDiffUtilCallback
import kotlinx.android.synthetic.main.series_fav_item.view.*

class FavSeriesAdapter: RecyclerView.Adapter<FavSeriesAdapter.ViewHolder>() {

    private val listSeries : MutableList<SeriesEntity> = mutableListOf()

    fun setSeriesFav(items: List<SeriesEntity>){
        val diffUtilCallback = SeriesDiffUtilCallback(listSeries,items)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listSeries.clear()
        listSeries.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavSeriesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.series_fav_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavSeriesAdapter.ViewHolder, position: Int) {
        holder.bind(listSeries[position])
    }

    override fun getItemCount(): Int {
        return listSeries.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(seriesEntity: SeriesEntity) {
            with(itemView){
                tvTitleItem.text = seriesEntity.name
                tvDateItem.text = seriesEntity.firstAirDate
                Glide.with(this)
                    .load(ApiHelper.IMAGE_POSTER_URL + seriesEntity.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
                    .into(imgItem)
                setOnClickListener {
                    val i = Intent(context,DetailSeriesActivity::class.java).apply {
                        putExtra(DetailSeriesActivity.EXTRA_SERIES_ID,seriesEntity.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }

    }
}