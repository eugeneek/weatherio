package com.eugeneek.wheatherio.feature.core.domain

import android.content.res.Resources
import android.os.Build

class ResourceManager(
    private var resources: Resources
) {

    val percentSign: String = "%"
    val pressureSign: String = "mmHg"
    val speedSign: String = "m/s"
    val degreeSign: String = "°"

    fun updateResources(resources: Resources) {
        this.resources = resources
    }

    fun getString(stringRes: Int): String = resources.getString(stringRes)
    fun getString(stringRes: Int, vararg args: Any): String = resources.getString(stringRes, *args)

    fun getColor(colorRes: Int): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(colorRes, null)
    } else {
        @Suppress("DEPRECATION")
        resources.getColor(colorRes)
    }
}