package nitrosrus.ru.dogapitest.mvp.model.breedsModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageBreedsList(
    @Expose val message: List<String>,
) : Parcelable