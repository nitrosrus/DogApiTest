package com.example.dogapitest.mvp.model.cache.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BreedsConverter {

//    @TypeConverter
//    fun breedsMapToString(breeds: Map<String, List<String>>): String {
//        var breed: String
//        var subbreed: String
//
//        breeds.keys.forEach { tut ->let { breed=tut } }
//        for ((key,value)in breeds)
//            value.let { it-> }
//
//
//    }


    @TypeConverter
    fun fromString(breeds: String?): Map<String, String> {
        val mapType = object : TypeToken<Map<String?, String?>?>() {}.type
        return Gson().fromJson(breeds, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: Map<String?, String?>?): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}