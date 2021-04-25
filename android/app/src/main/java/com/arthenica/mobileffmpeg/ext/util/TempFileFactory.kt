package com.arthenica.mobileffmpeg.ext.util

import java.io.File

/**
 * 创建临时文件
 * weiping@atlasv.com
 * 3/17/21
 */
interface TempFileFactory {
    fun createTempFile(prefix: String, suffix: String): File?
    fun clean(): Boolean
}