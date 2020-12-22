package id.husni.moviecatalogue.ui.favourite.series

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
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import id.husni.moviecatalogue.ui.detail.series.DetailSeriesActivity
import id.husni.moviecatalogue.utils.ApiHelper
import kotlinx.android.synthetic.main.series_item.view.*

class FavSeriesAdapter: PagedListAdapter<SeriesEntity,FavSeriesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<SeriesEntity> = object : DiffUtil.ItemCallback<SeriesEntity>(){
            override fun areItemsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavSeriesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.series_fav_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavSeriesAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position) as SeriesEntity)
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(seriesEntity: SeriesEntity) {
            with(itemView){
                tvTitleItem.text = seriesEntity.name
                tvDateItem.text = seriesEntity.firstAirDate
                Glide.with(context)
                    .load(ApiHelper.IMAGE_POSTER_URL + seriesEntity.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
                    .into(imgItem)
                setOnClickListener {
                    val i = Intent(context, DetailSeriesActivity::class.java).apply {
                        putExtra(DetailSeriesActivity.EXTRA_SERIES_ID, seriesEntity.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }

    }
}