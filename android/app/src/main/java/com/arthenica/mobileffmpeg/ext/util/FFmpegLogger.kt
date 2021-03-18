package com.arthenica.mobileffmpeg.ext.util

import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.Level
import com.arthenica.mobileffmpeg.LogMessage

/**
 * weiping@atlasv.com
 * 3/12/21
 */
object FFmpegLogger {
    const val HEAD_LINE = "::\n┌------------------------------------------------------------------------------------------------------┐\n"
    const val BOTTOM_LINE = "└------------------------------------------------------------------------------------------------------┘\n"

    @JvmStatic
    fun log(logMessage: () -> String) {
        Config.logCallbackFunction?.apply(LogMessage(0, Level.AV_LOG_INFO, logMessage()))
    }

    fun logCmdHeader(cmd: String) {
        Config.logCallbackFunction ?: return
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
        Config.logCallbackFunction ?: return
        val builder = StringBuilder()
        builder.append(HEAD_LINE)
        builder.append("|FFmpeg run end: code=$code\n")
        builder.append(getCmdInfo(cmd))
        builder.append("|take time: $timeMillis(ms)\n")
        builder.append(BOTTOM_LINE)
        log { builder.toString() }
    }
}