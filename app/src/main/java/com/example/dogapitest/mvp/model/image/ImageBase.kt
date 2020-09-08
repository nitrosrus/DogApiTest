import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize



@Parcelize
data class ImageBase (

	@Expose val message : List<String>,
	@Expose val status : String
) : Parcelable