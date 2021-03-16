package com.arthenica.mobileffmpeg.ext.filter.audio

import com.arthenica.mobileffmpeg.ext.filter.MediaFilter

/**
 * Audio speed
 * [atempo](https://ffmpeg.org/ffmpeg-all.html#atempo)
 * weiping@atlasv.com
 * 3/16/21
 */
class ATempo(val speed: Float) : MediaFilter() {
    override fun getFilterDesc(): String {
        return "atempo=$speed"
    }
}