package com.arthenica.mobileffmpeg.ext.cmd

import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.ext.duration.Duration
import com.arthenica.mobileffmpeg.ext.filter.MediaFilter
import com.arthenica.mobileffmpeg.ext.option.MediaOption
import com.arthenica.mobileffmpeg.ext.source.FileSource
import com.arthenica.mobileffmpeg.ext.source.Source
import com.arthenica.mobileffmpeg.ext.util.FFmpegLogger
import java.io.File

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class MobileFFmpeg {
    private val parts = arrayListOf<String>()
    fun format(format: String): MobileFFmpeg {
        parts.add("-f $format")
        return this
    }

    fun input(source: Source): MobileFFmpeg {
        parts.add("-i '$source'")
        return this
    }

    fun input(file: File): MobileFFmpeg {
        return input(FileSource(file))
    }

    fun duration(duration: Duration): MobileFFmpeg {
        parts.add("-t $duration")
        return this
    }

    fun videoCodec(codec: String): MobileFFmpeg {
        parts.add("-c:v $codec")
        return this
    }

    fun audioCodec(codec: String): MobileFFmpeg {
        parts.add("-c:a $codec")
        return this
    }

    fun option(key: String, value: String): MobileFFmpeg {
        parts.add("$key $value")
        return this
    }

    fun option(option: MediaOption): MobileFFmpeg {
        parts.add(option.toString())
        return this
    }

    fun output(path: String, overwrite: Boolean = true): MobileFFmpeg {
        parts.add(if (overwrite) "-y '$path'" else "'$path'")
        return this
    }

    fun output(file: File, overwrite: Boolean = true): MobileFFmpeg {
        return output(file.absolutePath, overwrite)
    }

    fun audioFilter(filter: MediaFilter): MobileFFmpeg {
        parts.add("-af $filter")
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
        return exec(this.build())
    }

    companion object {
        fun exec(cmd: String): Int {
            synchronized(MobileFFmpeg::class.java) {
                FFmpegLogger.log { "---------------------- run FFmpeg Cmd start ----------------------\n[$cmd]\n" }
                return FFmpeg.execute(cmd).also {
                    FFmpegLogger.log { "---------------------- run FFmpeg Cmd end code=($it) ----------------------\n" }
                }
            }
        }
    }
}