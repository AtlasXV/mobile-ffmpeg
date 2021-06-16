package com.arthenica.mobileffmpeg.ext.cmd

import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.ext.duration.Duration
import com.arthenica.mobileffmpeg.ext.filter.MediaFilter
import com.arthenica.mobileffmpeg.ext.option.MediaOption
import com.arthenica.mobileffmpeg.ext.source.FileSource
import com.arthenica.mobileffmpeg.ext.source.Source
import com.arthenica.mobileffmpeg.ext.util.FFmpegLogger
import com.arthenica.mobileffmpeg.ext.util.TempFileFactory
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
        parts.add("-i $source")
        return this
    }

    fun input(file: File): MobileFFmpeg {
        return input(FileSource(file))
    }

    fun duration(duration: Duration): MobileFFmpeg {
        parts.add("-t $duration")
        return this
    }

    fun codec(codec: String): MobileFFmpeg {
        parts.add("-c $codec")
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

    fun audioFilter(filter: MediaFilter): MobileFFmpeg {
        parts.add("-af $filter")
        return this
    }

    fun output(path: String, overwrite: Boolean = true): MobileFFmpeg {
        parts.add(if (overwrite) "-y '$path'" else "'$path'")
        return this
    }

    fun output(file: File, overwrite: Boolean = true): MobileFFmpeg {
        return output(file.absolutePath, overwrite)
    }

    fun asMp4(prefix: String = ""): File? {
        return asFile(prefix = prefix, suffix = ".mp4")
    }

    fun asFile(prefix: String = "", suffix: String): File? {
        return intoFile(createTempFile(prefix = prefix, suffix = suffix))
    }

    fun intoFile(outputFile: File): File? {
        return outputFile.takeIf {
            output(it)
            exec() == 0
        }
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
        private lateinit var tempFileFactory: TempFileFactory
        var ENABLE_LOG = false

        fun setTempFileFactory(factory: TempFileFactory) {
            this.tempFileFactory = factory
        }

        fun cleanTempFiles(){
            tempFileFactory.clean()
        }

        fun createTempFile(prefix: String = "temp_", suffix: String): File {
            return tempFileFactory.createTempFile(prefix, suffix)
        }

        fun exec(cmd: String): Int {
            synchronized(MobileFFmpeg::class.java) {
                val startTimeMillis = if (ENABLE_LOG) System.currentTimeMillis() else 0
                FFmpegLogger.logCmdHeader(cmd)
                val code = FFmpeg.execute(cmd)
                val endTimeMillis = if (ENABLE_LOG) System.currentTimeMillis() else 0
                FFmpegLogger.logCmdBottom(cmd, code, endTimeMillis - startTimeMillis)
                return code
            }
        }
    }
}