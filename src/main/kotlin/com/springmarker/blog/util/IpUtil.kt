package com.springmarker.blog.util

import javax.servlet.http.HttpServletRequest

/**
 * IP工具类
 *
 * @author  Springmarker
 * @date  2019/7/2 13:30
 */
object IpUtil {

    /**
     * Proxy-Client-IP：apache 服务代理
     * WL-Proxy-Client-IP：weblogic 服务代理
     * X-Forwarded-For：Squid 服务代理
     * HTTP_CLIENT_IP：有些代理服务器
     * X-Real-IP：nginx服务代理
     */
    private val ipHeaders = listOf<String>("X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP")

    /**
     * 获取真实IP的方法
     */
    fun getRealIp(request: HttpServletRequest): String {
        var ip: String? = null
        ipHeaders.forEach {
            if (ip == null || ip!!.isEmpty() || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader(it)
            }
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ip != null && ip!!.isNotEmpty() && ip!!.contains(",")) {
            ip = ip!!.split(",")[0]
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip!!.isEmpty() || "unknown".equals(ip!!, ignoreCase = true)) {
            ip = request.remoteAddr
        }
        return ip ?: ""
    }
}