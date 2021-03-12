package com.arthenica.mobileffmpeg.ext.source.audio

/**
 * [anullsrc](https://ffmpeg.org/ffmpeg-all.html#anullsrc)
 * weiping@atlasv.com
 * 3/12/21
 */
class ANullSrc : AudioSource() {

    override fun toString(): String {
        return "anullsrc=channel_layout=$channelLayout:sample_rate=$sampleRate"
    }
}