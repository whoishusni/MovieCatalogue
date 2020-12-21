package id.husni.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import kotlinx.android.parcel.Parcelize

data class MoviesResponse(

	@field:SerializedName("results")
	val resultMovies: List<MoviesEntity>
)