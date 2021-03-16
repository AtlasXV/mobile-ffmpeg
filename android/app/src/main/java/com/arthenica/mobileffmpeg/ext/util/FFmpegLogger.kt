package com.arthenica.mobileffmpeg.ext.util

import android.util.Log
import com.arthenica.mobileffmpeg.Config

/**
 * weiping@atlasv.com
 * 3/12/21
 */
object FFmpegLogger {
    @JvmStatic
    fun log(logMessage: () -> String) {
        if (Config.ENABLE_LOG) {
            Log.d(Config.TAG, logMessage())
        }
    }
}