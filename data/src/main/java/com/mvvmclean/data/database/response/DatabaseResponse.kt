package com.mvvmclean.data.database.response

import com.google.gson.annotations.SerializedName
import com.mvvmclean.data.service.response.CharacterResponse

class DatabaseResponse<T>(

    @SerializedName("results") val characters: List<CharacterResponse>,
            val offset: Int,
            val limit: Int,
            val total: Int
)