package com.arthenica.mobileffmpeg.ext.filter

import com.arthenica.mobileffmpeg.ext.filter.FadeOption.Companion.IN
import com.arthenica.mobileffmpeg.ext.filter.FadeOption.Companion.OUT

/**
 * weiping@atlasv.com
 * 3/15/21
 */
class FadeFilter(val fadeType: String) : MediaFilter() {

    protected var fadeInOption = FadeOption(IN)
    protected var fadeOutOption = FadeOption(OUT)

    fun fadeIn(): FadeOption {
        return fadeInOption
    }

    fun fadeOut(): FadeOption {
        return fadeOutOption
    }

    override fun getFilterDesc(): String {
        val fadeIn = fadeInOption.toString().let { if (it.isNotEmpty()) "afade=$it" else "" }
        val fadeOut = fadeOutOption.toString().let { if (it.isNotEmpty()) "afade=$it" else "" }
        val spliterator = if (fadeIn.isNotEmpty() && fadeOut.isNotEmpty()) "," else ""
        return "$fadeIn$spliterator$fadeOut"
    }

    companion object {
        const val FADE_TYPE_AUDIO = "afade"
        const val FADE_TYPE_VIDEO = "fade"
        fun fade(): FadeFilter {
            return FadeFilter(FADE_TYPE_VIDEO)
        }

        fun aFade(): FadeFilter {
            return FadeFilter(FADE_TYPE_AUDIO)
        }
    }
}