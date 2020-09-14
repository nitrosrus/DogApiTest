package com.example.dogapitest.mvp.model.breedsModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubBreedsList(
    @Expose
    val message: List<String>, //subbreedlist
    @Expose val status: String
) : Parcelable