package com.arthenica.mobileffmpeg.ext.source

import java.io.File

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class FileSource(private val filePath: File) : Source() {
    override fun toString(): String {
        return filePath.absolutePath
    }
}