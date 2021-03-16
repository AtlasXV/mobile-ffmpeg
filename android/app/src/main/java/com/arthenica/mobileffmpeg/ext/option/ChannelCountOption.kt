package com.arthenica.mobileffmpeg.ext.option

/**
 * weiping@atlasv.com
 * 3/16/21
 */
class ChannelCountOption(private val channelCount: Int) : AudioOption() {
    override fun toString(): String {
        return "-ac $channelCount"
    }
}