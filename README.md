# MyBlog
我的博客系统

##介绍　
　　1.本项目是使用java开发的自用博客系统，java框架使用的是经典的SpringMVC,Spring,Mybatis。<br>
　　2.集成了七牛的SDK，由于带宽有限，项目启动时，会自动将静态文件上传到CDN中。<br>
　　3.项目运行时，被修改的项目文件同样会同步到七牛CDN中，节省服务器带宽的同时对开发更友好。<br>
　　4.使用了Redis进行缓存，减小短时间内并发的负载。<br>
　　5.为了减小使用者恶意调用api，使用了ip拉黑的方法，使用者短时间内使用api过高时会触发拉黑，无法正常使用。<br>
　　6.为了防止恶意刷评论，同样限制ip一段时间内无法连续评论。<br>
##地址　　
　　地址：[博客](https://blog.springmarker.com)
