package com.arthenica.mobileffmpeg.ext.filter.audio

import com.arthenica.mobileffmpeg.ext.filter.MediaFilter

/**
 * @param value 0 to 1.0
 * weiping@atlasv.com
 * 3/16/21
 */
class VolumeFilter(private val value: Float) : MediaFilter() {
    override fun getFilterDesc(): String {
        return "volume=$value"
    }
}