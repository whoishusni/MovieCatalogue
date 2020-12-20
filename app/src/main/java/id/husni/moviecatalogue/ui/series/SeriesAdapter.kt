package id.husni.moviecatalogue.ui.series

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries
import id.husni.moviecatalogue.ui.detail.series.DetailSeriesActivity
import id.husni.moviecatalogue.utils.ApiHelper
import kotlinx.android.synthetic.main.series_item.view.*

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {
    private val series = ArrayList<ResultsSeries>()

    fun setSeries(items : List<ResultsSeries>?){
        if (items.isNullOrEmpty()) return
        series.clear()
        series.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.series_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesAdapter.ViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int = series.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(series: ResultsSeries) {
            with(itemView){
                tvTitleItem.text = series.name
                tvDateItem.text = series.firstAirDate
                Glide.with(context)
                        .load(ApiHelper.IMAGE_POSTER_URL + series.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_broken).error(R.drawable.ic_broken))
                        .into(imgItem)
                setOnClickListener {
                    val i = Intent(context, DetailSeriesActivity::class.java).apply {
                        putExtra(DetailSeriesActivity.EXTRA_SERIES_ID, series.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }

    }
}