package com.grofin.base.networking

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.annotations.Expose
import javax.inject.Inject

class SerializationStrategy @Inject constructor(): ExclusionStrategy {
    override fun shouldSkipField(fieldAttributes: FieldAttributes?): Boolean {
        val expose = fieldAttributes?.getAnnotation(Expose::class.java)
        return expose != null && !expose.serialize
    }

    override fun shouldSkipClass(clazz: Class<*>?) = false
}