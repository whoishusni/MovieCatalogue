package id.husni.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

data class SeriesResponse(

	@field:SerializedName("results")
	val results: List<SeriesEntity>
)