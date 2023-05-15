import com.example.appcenttest.Model.Artist
import com.example.appcenttest.Model.Contributors
import com.example.appcenttest.Model.Genres
import com.example.appcenttest.Model.Tracks
import com.example.appcenttest.Model.TracksList
import com.google.gson.annotations.SerializedName

data class AlbumDetail(
    val id: Long,
    val title: String,
    val upc: String,
    val link: String,
    val share: String,
    val cover: String,
    val cover_small: String,
    val cover_medium: String,
    val cover_big: String,
    val cover_xl: String,
    val md5_image: String,
    val genre_id: Int,
    val genres: Genres,
    val label: String,
    val nb_tracks: Int,
    val duration: Int,
    val fans: Int,
    val release_date: String,
    val record_type: String,
    val available: Boolean,
    val tracklist: String,
    val explicit_lyrics: Boolean,
    val explicit_content_lyrics: Int,
    val explicit_content_cover: Int,
    val contributors: List<Contributors>,
    val artist: Artist,
    val type: String,
    val tracks: TracksList,
)