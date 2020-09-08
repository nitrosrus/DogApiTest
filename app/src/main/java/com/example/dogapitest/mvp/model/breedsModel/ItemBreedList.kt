package com.example.dogapitest.mvp.model.breedsModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemBreedList(
    @Expose
    val anyBreeds: List<String>

) : Parcelable