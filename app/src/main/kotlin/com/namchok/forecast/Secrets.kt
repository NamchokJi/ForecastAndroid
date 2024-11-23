package com.namchok.forecast

class Secrets {

    // Method calls will be added by gradle task hideSecret
    // Example : external fun getWellHiddenSecret(packageName: String): String

    companion object {
        const val PACKAGE_NAME = "com.namchok.forecast"
        init {
            System.loadLibrary("secrets")
        }
    }

    external fun getapiKey(packageName: String): String

    val apiKey = getapiKey(PACKAGE_NAME)
}