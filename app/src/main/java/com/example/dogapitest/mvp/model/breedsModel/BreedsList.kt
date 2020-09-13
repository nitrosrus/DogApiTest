package com.example.dogapitest.mvp.model.breedsModel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize

data class BreedsList(
	@Expose val message: Map<String,List<String>>,
	@Expose val status: String
) : Parcelable