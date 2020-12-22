package id.husni.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import kotlinx.android.parcel.Parcelize

data class SeriesResponse(

	@field:SerializedName("results")
	val results: List<SeriesEntity>
)