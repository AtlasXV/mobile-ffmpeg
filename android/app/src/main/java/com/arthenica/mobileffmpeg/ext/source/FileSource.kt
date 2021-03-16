package com.arthenica.mobileffmpeg.ext.source

import java.io.File

/**
 * weiping@atlasv.com
 * 3/12/21
 */
class FileSource : Source {
    private var filePath: String = ""

    constructor(file: File) : this(file.absolutePath)
    constructor(filePath: String) : super() {
        this.filePath = filePath
    }

    override fun toString(): String {
        return filePath
    }
}