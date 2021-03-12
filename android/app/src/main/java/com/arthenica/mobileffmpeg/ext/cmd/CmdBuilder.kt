package com.arthenica.mobileffmpeg.ext.cmd

import com.arthenica.mobileffmpeg.ext.common.runFFmpegCmd
import com.arthenica.mobileffmpeg.ext.duration.Duration
import com.arthenica.mobileffmpeg.ext.source.Source

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class CmdBuilder {
    private val parts = arrayListOf<String>()
    fun format(format: String): CmdBuilder {
        parts.add("-f $format")
        return this
    }

    fun input(source: Source): CmdBuilder {
        parts.add("-i '$source'")
        return this
    }

    fun duration(duration: Duration): CmdBuilder {
        parts.add("-t $duration")
        return this
    }

    fun videoCodec(codec: String): CmdBuilder {
        parts.add("-c:v $codec")
        return this
    }

    fun audioCodec(codec: String): CmdBuilder {
        parts.add("-c:a $codec")
        return this
    }

    fun output(path: String, overwrite: Boolean = false): CmdBuilder {
        parts.add(if (overwrite) "-y '$path'" else "'$path'")
        return this
    }

    fun build(): String {
        val content = StringBuilder()
        parts.forEachIndexed { index, s ->
            content.append(s)
            if (index < parts.size - 1) {
                content.append(" ")
            }
        }
        return content.toString()
    }

    fun exec(): Int {
        return runFFmpegCmd(this.build())
    }
}