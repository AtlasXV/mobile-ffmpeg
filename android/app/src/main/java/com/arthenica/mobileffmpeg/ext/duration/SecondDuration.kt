package com.arthenica.mobileffmpeg.ext.duration

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class SecondDuration(val seconds: Long) : Duration() {
    override fun toString(): String {
        return seconds.toString()
    }
}