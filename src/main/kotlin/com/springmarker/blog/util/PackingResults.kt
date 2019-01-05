package com.springmarker.blog.util

import java.util.*

/**
 * @author Springmarker
 * @date 2018/10/6 21:05
 */
object PackingResults {

    fun toSuccessMap(list: List<*>): Map<*, *> {
        val result = HashMap<Any, Any>()
        result["resultCode"] = 200
        result["data"] = list
        return result
    }

    fun toSuccessMap(map: Map<*, *>): Map<*, *> {
        val result = HashMap<Any, Any>()
        result["resultCode"] = 200
        result["data"] = map
        return result
    }

    fun toSuccessMap(): Map<*, *> {
        val result = HashMap<Any, Any>()
        result["resultCode"] = 200
        return result
    }

    fun toWorngMap(detailed: String): Map<*, *> {
        val result = HashMap<Any, Any>()
        result["resultCode"] = 500
        result["wrong"] = detailed
        return result
    }

}
