package com.arthenica.mobileffmpeg.ext.source

import java.io.File

/**
 * [Concatenate](https://trac.ffmpeg.org/wiki/Concatenate)
 * weiping@atlasv.com
 * 3/17/21
 */
class ConcatSource(private val fileList: List<File>) : Source() {
    override fun toString(): String {
        val builder = StringBuilder("concat:")
        fileList.forEachIndexed { index, file ->
            builder.append("'${file.absolutePath}'")
            if (index != fileList.size - 1) {
                builder.append("|")
            }
        }
        return builder.toString()
    }
}