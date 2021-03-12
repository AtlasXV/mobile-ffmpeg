package com.arthenica.mobileffmpeg.ext.source.audio

import com.arthenica.mobileffmpeg.ext.source.Source

/**
 * weiping@atlasv.com
 * 3/12/21
 */
open class AudioSource : Source() {
    protected var channelLayout: String = ChannelLayouts.STEREO
    protected var sampleRate: Long = 44100
}