package id.husni.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import kotlinx.android.parcel.Parcelize

data class MoviesResponse(

	@field:SerializedName("results")
	val results: List<MoviesResult>
)

@Parcelize
data class MoviesResult(
	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	) : Parcelable
