package com.arthenica.mobileffmpeg.ext.duration

/**
 * [time-duration-syntax](https://ffmpeg.org/ffmpeg-utils.html#time-duration-syntax)
 * weiping@atlasv.com
 * 3/12/21
 */
abstract class Duration {
    abstract fun hasDuration(): Boolean
}