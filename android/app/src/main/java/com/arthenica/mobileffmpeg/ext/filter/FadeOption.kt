package com.arthenica.mobileffmpeg.ext.filter

import com.arthenica.mobileffmpeg.ext.duration.Duration
import com.arthenica.mobileffmpeg.ext.duration.SecondDuration
import com.arthenica.mobileffmpeg.ext.filter.FadeOption.Companion.IN
import com.arthenica.mobileffmpeg.ext.filter.FadeOption.Companion.OUT

/**
 * @param type [IN], [OUT]
 * weiping@atlasv.com
 * 3/15/21
 */
class FadeOption(val type: String) {

    private var startTime: Duration = SecondDuration(0f)
    private var duration: Duration = SecondDuration(0f)

    fun startAtSeconds(seconds: Float): FadeOption {
        startTime = SecondDuration(seconds)
        return this
    }

    fun durationSeconds(seconds: Float): FadeOption {
        duration = SecondDuration(seconds)
        return this
    }

    override fun toString(): String {
        return if (duration.hasDuration()) {
            "t=$type:st=$startTime:d=$duration"
        } else {
            ""
        }
    }

    companion object {
        //  fade-in
        const val IN = "in"
        const val OUT = "out"
    }
}