package `in`.androidcodes.lastfmclone.model.top

import com.google.gson.annotations.SerializedName


data class Toptags(

    @SerializedName("@attr")
    val attr: Attr,

    @SerializedName("tag")
    val tag: List<Tag>
)