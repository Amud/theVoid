package com.amud.thevoid.utils

import com.amud.thevoid.AppController
import java.io.IOException
import java.io.InputStream

class FileUtils {
    private fun readStream(fileName: String): InputStream? {
        var inputStream: InputStream? = null
        try {
            inputStream = AppController.getInstance().assets?.open(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return inputStream
    }

}