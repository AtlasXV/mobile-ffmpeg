package com.arthenica.mobileffmpeg.ext.source.audio

import com.arthenica.mobileffmpeg.ext.option.ChannelLayouts
import com.arthenica.mobileffmpeg.ext.option.SampleRateOption
import com.arthenica.mobileffmpeg.ext.source.Source

/**
 * weiping@atlasv.com
 * 3/12/21
 */
open class AudioSource : Source() {
    protected var channelLayout: String = ChannelLayouts.STEREO
    protected var sampleRate = SampleRateOption.COMMON_RATE
}