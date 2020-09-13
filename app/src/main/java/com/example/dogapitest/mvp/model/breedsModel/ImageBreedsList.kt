package com.example.dogapitest.mvp.model.breedsModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.lang.StringBuilder


@Parcelize
data class ImageBreedsList(
    @Expose val message: List<String>,  //url image
    @Expose val status: String
) : Parcelable