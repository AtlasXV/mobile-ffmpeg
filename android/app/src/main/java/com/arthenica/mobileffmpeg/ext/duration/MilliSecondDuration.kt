package com.arthenica.mobileffmpeg.ext.duration

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class MilliSecondDuration(var millis: Float) : Duration() {
    override fun hasDuration(): Boolean {
        return millis > 0
    }

    override fun toString(): String {
        return "${millis}ms"
    }
}