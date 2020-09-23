package com.example.dogapitest.mvp.model.breedsModel

import android.os.Parcelable

import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ImageBreedsList(
    @Expose val message: List<String>,  //url image
) : Parcelable