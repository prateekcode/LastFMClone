package `in`.androidcodes.lastfmclone.model.topgenre

import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("num_res")
    val numRes: Int,
    val offset: Int,
    val total: Int
)
