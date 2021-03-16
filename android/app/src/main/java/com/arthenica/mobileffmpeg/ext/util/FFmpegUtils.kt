package com.arthenica.mobileffmpeg.ext.util

import com.arthenica.mobileffmpeg.ext.cmd.MobileFFmpeg
import com.arthenica.mobileffmpeg.ext.codec.Codecs
import com.arthenica.mobileffmpeg.ext.duration.SecondDuration
import com.arthenica.mobileffmpeg.ext.format.Formats
import com.arthenica.mobileffmpeg.ext.source.FileSource
import com.arthenica.mobileffmpeg.ext.source.audio.ANullSrc
import java.io.File

/**
 * weiping@atlasv.com
 * 3/12/21
 */

object FFmpegUtils {
    fun mixMuteAudio(inputFile: File, outputFile: File, seconds: Float, overwrite: Boolean = true): Int {
        return MobileFFmpeg()
                .format(Formats.LAVFI)
                .input(ANullSrc())
                .input(FileSource(inputFile))
                .duration(SecondDuration(seconds))
                .videoCodec(Codecs.COPY)
                .audioCodec(Codecs.AAC)
                .output(outputFile.absolutePath, overwrite = overwrite)
                .exec()
    }
}