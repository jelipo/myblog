package com.jelipo.blog.util

/**
 * 包装结果类
 * @author Jelipo
 * @date 2018/10/6 21:05
 */
object PackingResults {

    fun toSuccessMap(list: List<*>): Map<*, *> {
        return mapOf("resultCode" to 200, "data" to list)
    }

    fun toSuccessMap(map: Map<*, *>): Map<*, *> {
        return mapOf("resultCode" to 200, "data" to map)
    }

    fun toSuccessMap(): Map<*, *> {
        return mapOf("resultCode" to 200)
    }

    fun toWorngMap(detailed: String): Map<*, *> {
        return mapOf("resultCode" to 500, "wrong" to detailed)
    }
}