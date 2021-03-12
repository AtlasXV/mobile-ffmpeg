package com.arthenica.mobileffmpeg.ext.common

import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.ext.util.FFmpegLogger.ffmpegLog

/**
 * weiping@atlasv.com
 * 3/12/21
 */
fun runFFmpegCmd(cmd: String): Int {
    synchronized(FFmpeg::class.java) {
        ffmpegLog { "---------------------- run FFmpeg Cmd start ----------------------\n[$cmd]\n" }
        return FFmpeg.execute(cmd).also {
            ffmpegLog { "---------------------- run FFmpeg Cmd end code=($it) ----------------------\n[$cmd]\n" }
        }
    }
}