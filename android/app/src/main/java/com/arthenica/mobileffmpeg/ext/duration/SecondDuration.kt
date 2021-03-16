package com.arthenica.mobileffmpeg.ext.duration

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class SecondDuration(var seconds: Float) : Duration() {
    override fun hasDuration(): Boolean {
        return seconds > 0
    }

    override fun toString(): String {
        return seconds.toString()
    }
}