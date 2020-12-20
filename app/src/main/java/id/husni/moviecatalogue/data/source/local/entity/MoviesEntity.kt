package id.husni.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies_favourite")
@Parcelize
data class MoviesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    @field:SerializedName("release_date")
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "bookmarked")
    val bookmarked: Boolean
): Parcelable