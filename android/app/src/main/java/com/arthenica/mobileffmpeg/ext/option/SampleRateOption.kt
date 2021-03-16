package com.arthenica.mobileffmpeg.ext.option

/**
 * weiping@atlasv.com
 * 3/16/21
 */
class SampleRateOption(private val sampleRate: Int) : AudioOption() {
    override fun toString(): String {
        return "-ar $sampleRate"
    }

    companion object {
        /**
         * [wiki-44,100_Hz](https://en.wikipedia.org/wiki/44,100_Hz)
         */
        const val COMMON_RATE = 44100
        private val COMMON_RATE_OPTION = SampleRateOption(COMMON_RATE)
        fun common(): SampleRateOption {
            return COMMON_RATE_OPTION
        }
    }
}