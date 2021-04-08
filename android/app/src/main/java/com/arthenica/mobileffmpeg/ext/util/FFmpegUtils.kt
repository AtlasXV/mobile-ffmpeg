package com.arthenica.mobileffmpeg.ext.util

import com.arthenica.mobileffmpeg.ext.cmd.MobileFFmpeg
import com.arthenica.mobileffmpeg.ext.codec.Codecs
import com.arthenica.mobileffmpeg.ext.duration.SecondDuration
import com.arthenica.mobileffmpeg.ext.format.Formats
import com.arthenica.mobileffmpeg.ext.source.ConcatSource
import com.arthenica.mobileffmpeg.ext.source.audio.ANullSrc
import java.io.File

/**
 * weiping@atlasv.com
 * 3/12/21
 */

object FFmpegUtils {
    fun mixMuteAudio(inputFile: File, seconds: Float): File? {
        return MobileFFmpeg()
                .format(Formats.LAVFI)
                .input(ANullSrc())
                .input(inputFile)
                .duration(SecondDuration(seconds))
                .videoCodec(Codecs.COPY)
                .audioCodec(Codecs.AAC)
                .asMp4(prefix = "mix_mute_audio_")
    }

    /**
     * [Concatenate#protocol](https://trac.ffmpeg.org/wiki/Concatenate#protocol)
     */
    fun concatMp4(fileList: List<File>): File? {
        if (fileList.size <= 1) {
            return fileList.getOrNull(0)
        }
        val tsFiles = fileList.mapNotNull {
            val outputFile = MobileFFmpeg.createTempFile(suffix = ".ts")
            val code = MobileFFmpeg().input(it)
                    .codec(Codecs.COPY)
                    .option("-bsf:v", "h264_mp4toannexb")
                    .format(Formats.MPEGTS)
                    .output(outputFile)
                    .exec()
            if (code == 0) {
                outputFile
            } else {
                null
            }
        }
        if (tsFiles.isEmpty()) {
            return null
        }
        return MobileFFmpeg()
                .input(ConcatSource(tsFiles))
                .codec(Codecs.COPY)
                .option("-bsf:a", "aac_adtstoasc")
                .asMp4(prefix = "concat_")
    }
}