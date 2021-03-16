package com.arthenica.mobileffmpeg.ext.filter

import com.arthenica.mobileffmpeg.ext.option.MapOption

/**
 * weiping@atlasv.com
 * 3/15/21
 */
abstract class MediaFilter {
    private var mapOption: MapOption? = null
    private var mappingName: String? = null

    fun map(): MapOption {
        val mapOption = MapOption()
        this.mapOption = mapOption
        return mapOption
    }

    fun renameTo(name: String) {
        mappingName = name
    }

    abstract fun getFilterDesc(): String

    override fun toString(): String {
        return "'${mapOption?.toString() ?: ""}${getFilterDesc()}${mappingName ?: ""}'"
    }
}