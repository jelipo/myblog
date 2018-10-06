package com.springmarker.blog.conf

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

/**
 * @author Springmarker
 * @date 2018/10/4 20:50
 */
@Configuration
class FreeMarkerConf {

    @Autowired
    private lateinit var conf: freemarker.template.Configuration

    @PostConstruct
    fun setRandomStr() {
        val random = RandomStringUtils.random(8, true, true)
        conf.setSharedVariable("randomStr", random)
    }

}