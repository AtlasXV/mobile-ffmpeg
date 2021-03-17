package com.arthenica.mobileffmpeg.ext.util

import android.util.Log
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.ext.cmd.MobileFFmpeg.Companion.ENABLE_LOG

/**
 * weiping@atlasv.com
 * 3/12/21
 */
object FFmpegLogger {
    const val HEAD_LINE = "::\n┌------------------------------------------------------------------------------------------------------┐\n"
    const val BOTTOM_LINE = "└------------------------------------------------------------------------------------------------------┘\n"

    @JvmStatic
    fun log(logMessage: () -> String) {
        if (ENABLE_LOG) {
            Log.d(Config.TAG, logMessage())
        }
    }

    fun logCmdHeader(cmd: String) {
        if (!ENABLE_LOG) {
            return
        }
        val builder = StringBuilder()
        builder.append(HEAD_LINE)
        builder.append("|FFmpeg run start\n")
        builder.append(getCmdInfo(cmd))
        builder.append(BOTTOM_LINE)
        log { builder.toString() }
    }

    private fun getCmdInfo(cmd: String): String {
        val builder = StringBuilder()
        val strList = cmd.split(" ")
        builder.append("| ")
        strList.forEachIndexed { index, s ->
            builder.append(s).append(" ")
            if (index < strList.size - 1 && s.length > 50) {
                builder.append("\n| ")
            }
        }
        return builder.append("\n").toString()
    }

    fun logCmdBottom(cmd: String, code: Int, timeMillis: Long) {
        if (!ENABLE_LOG) {
            return
        }
        val builder = StringBuilder()
        builder.append(HEAD_LINE)
        builder.append("|FFmpeg run end: code=$code\n")
        builder.append(getCmdInfo(cmd))
        builder.append("|take time: $timeMillis(ms)\n")
        builder.append(BOTTOM_LINE)
        log { builder.toString() }
    }
}