package com.arthenica.mobileffmpeg.ext.option

/**
 * [Advanced-options](https://ffmpeg.org/ffmpeg-all.html#Advanced-options)
 * weiping@atlasv.com
 * 3/16/21
 */
class MapOption : MediaOption() {
    private var inputIndex: Int = 0
    private var type: String = ""
    private var streamIndex = 0

    fun fromInput(index: Int): MapOption {
        inputIndex = index
        return this
    }

    fun selectAudio(index: Int = Int.MAX_VALUE): MapOption {
        streamIndex = index
        type = TYPE_AUDIO
        return this
    }

    fun selectVideo(index: Int = Int.MAX_VALUE): MapOption {
        streamIndex = index
        type = TYPE_VIDEO
        return this
    }

    override fun toString(): String {
        val parts = arrayListOf<String>()
        parts.add(inputIndex.toString())
        if (type.isNotEmpty()) {
            parts.add(type)
        }
        if (streamIndex != Int.MAX_VALUE) {
            parts.add(streamIndex.toString())
        }

        val builder = StringBuilder()
        parts.forEachIndexed { index, s ->
            builder.append(s)
            if (index != parts.size - 1) {
                builder.append(":")
            }
        }

        /**
         * -map 0:a:1
         */
        return "-map $builder"
    }

    companion object {
        const val TYPE_VIDEO = "v"
        const val TYPE_AUDIO = "a"
    }
}